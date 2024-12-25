//package com.DevPointSystem.Comptabilite.Authentification.Config;
//
//import com.DevPointSystem.Comptabilite.Authentification.service.JwtService;
//import com.DevPointSystem.Comptabilite.web.errors.IllegalBusinessLogiqueException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.common.base.Preconditions;
//import io.jsonwebtoken.ExpiredJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import javax.annotation.Nonnull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ProblemDetail;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @author Administrator
// */
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final HandlerExceptionResolver handlerExceptionResolver;
//
//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//
//    public JwtAuthenticationFilter(
//            JwtService jwtService,
//            UserDetailsService userDetailsService,
//            HandlerExceptionResolver handlerExceptionResolver
//    ) {
//        this.jwtService = jwtService;
//        this.userDetailsService = userDetailsService;
//        this.handlerExceptionResolver = handlerExceptionResolver;
//    }
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain
//    ) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        ProblemDetail errorDetail = null;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        try {
//            final String jwt = authHeader.substring(7);
//            final String userEmail = jwtService.extractUsername(jwt);
//
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            if (userEmail != null && authentication == null) {
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//
//                if (jwtService.isTokenValid(jwt, userDetails)) {
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            }
//
//            filterChain.doFilter(request, response);
//        } //        catch (Exception exception) {
//        ////              errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
//        ////              errorDetail.setProperty("description", "Session Token has expired V1");
//        //            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Access is denied.");
//        //             
//        ////            errorDetail.setStatus(HttpStatusCode.valueOf(401).value());
//        //        }
//        catch (ExpiredJwtException e) {
//            // Handle expired token (e.g., send a 401 error)
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////             filterChain.doFilter(request, response);
//
//// ObjectMapper mapper = new ObjectMapper();
////            JsonNode error = mapper.readTree(responseBody);
////            String description = error.get("message").asText();
////              throw new IllegalBusinessLogiqueException(description);
////            response.setHeader("Authentication", "Token has expired");
//        } catch (Exception e) {
//            // Handle other potential JWT errors (e.g., invalid token format)
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.setHeader("Authentication", "Invalid token");
//        }
//    }
//}
