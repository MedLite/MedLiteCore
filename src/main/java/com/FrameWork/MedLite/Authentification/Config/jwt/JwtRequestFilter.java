/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FrameWork.MedLite.Authentification.Config.jwt;

import com.FrameWork.MedLite.Authentification.service.JwtService;
import com.FrameWork.MedLite.web.errors.IllegalBusinessLogiqueException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        final String requestUri = request.getServletPath();

        String username = null;
        String jwtToken = null;

        if (requestUri.equals("/api/auth/login")  || requestUri.equals("/api/auth/signup") || requestUri.equals("/actuator")) {
            chain.doFilter(request, response);
            System.out.println("com.DevPointS" + requestUri);
            return; // Stop processing the request
        } else {
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                final String jwt = requestTokenHeader.substring(7);
                try {
                    // ... your existing code for extracting userEmail ... 

                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    final String userEmail = jwtService.extractUsername(jwt);
                    if (userEmail != null && authentication == null) {
                        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                        if (jwtService.isTokenValid(jwt, userDetails)) {
                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authToken);
                            try {
                                // Proceed with the request
                                chain.doFilter(request, response);
//                                System.out.println("V1");
                            } catch (Exception e) {
//                                    System.out.println("V2");
                                // Catch exceptions thrown by chain.doFilter()
                                if (e instanceof DataIntegrityViolationException) {
//                                        System.out.println("V3");
                                    // Handle database constraint violations
                                    logger.warn("Data integrity violation: " + e.getMessage());
                                    sendErrorResponse(response, HttpStatus.BAD_REQUEST, "Invalid data");
                                    return;
                                } else {
//                                        System.out.println("V4");
                                    // Handle other exceptions (potentially server errors)
                                    logger.error("Error processing request: " + e.getMessage(), e); // Log the full exception
                                    sendErrorResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, "Server error"); // 500 error for other issues
                                    return;
                                }
                            }
//                               System.out.println("V5");
                            // Only proceed with the request if the token is valid
                            return; // Stop processing the request
                        } else {
//                                System.out.println("V6");
                            // Token is not valid (expired or invalid)
                            logger.warn("JWT Token is not valid: " + jwt);
                            sendErrorResponse(response, HttpStatus.FORBIDDEN, "Token is not valid");
                            return; // Stop processing the request
                        }

                    } else {
                        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                            if (jwtTokenUtil.validateToken(jwtToken)) {
                                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, jwtToken, new ArrayList<>());
                                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                            }
                        }

                        // User is already authenticated
                        chain.doFilter(request, response);
                        return; // Stop processing the request
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to get JWT Token");
                    logger.warn("Unable to get JWT Token");
                    sendErrorResponse(response, HttpStatus.BAD_REQUEST, "Invalid token");
                    return; // Stop processing the request
                } catch (ExpiredJwtException e) {
                    System.out.println("JWT Token has expired");
                    logger.warn("JWT Token has expired");
                    sendErrorResponse(response, HttpStatus.FORBIDDEN, "Token has expired");
                    return; // Stop processing the request
                }

            } else {
                logger.warn("JWT Token does not begin with Bearer String");
                sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "Authorization header is missing or invalid");
                return;

            }
        }

    }

    private void sendErrorResponse(HttpServletResponse response, HttpStatus status, String message)
            throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", status.value());
        errorResponse.put("description", message);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(errorResponse));
    }

    private String traceResponseWhenError(ClientHttpResponse response) throws IOException {
//        log.error("============================response begin==========================================");
//        log.error("Status code  : {}", response.getStatusCode());
//        log.error("Status text      : {}", response.getStatusText());
//        log.error("Headers     : {}", response.getHeaders());

        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
//        log.error("Response body: {}", inputStringBuilder.toString());
//        log.error("=======================response end=================================================");
        return inputStringBuilder.toString();
    }

    private void shouldThrowIllegalBusinessException(ClientHttpResponse response, String responseBody) throws IOException {
        int statusCodeValue = response.getStatusCode().value();
        if (statusCodeValue == 409) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode error = mapper.readTree(responseBody);
            String description = error.get("description").asText();
            throw new IllegalBusinessLogiqueException(description);
        }
        if (statusCodeValue == 403) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode error = mapper.readTree(responseBody);
            String description = error.get("message").asText();
            throw new IllegalBusinessLogiqueException(description);
        }
        if (statusCodeValue == 401) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode error = mapper.readTree(responseBody);
            String description = error.get("message").asText();
            throw new IllegalBusinessLogiqueException(description);
        }

    }

}
