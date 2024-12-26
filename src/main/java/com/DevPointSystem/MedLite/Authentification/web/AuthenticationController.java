/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Authentification.web;

import com.DevPointSystem.MedLite.Authentification.domaine.User;
import com.DevPointSystem.MedLite.Authentification.dto.AccessUserDTO;
import com.DevPointSystem.MedLite.Authentification.dto.LoginResponse;
import com.DevPointSystem.MedLite.Authentification.dto.LoginUserDto;
import com.DevPointSystem.MedLite.Authentification.dto.RegisterUserDto;
import com.DevPointSystem.MedLite.Authentification.repository.UserRepository;
import com.DevPointSystem.MedLite.Authentification.service.AccessUserService;
import com.DevPointSystem.MedLite.Authentification.service.AuthenticationService;
import com.DevPointSystem.MedLite.Authentification.service.JwtService;
import com.DevPointSystem.MedLite.Authentification.service.UserDetailsImpl;
import jakarta.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
/**
 *
 * @author Administrator
 */

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {

    private static final String ENTITY_NAME = "auth";

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final AccessUserService accessUserService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, AccessUserService accessUserService, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.accessUserService = accessUserService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
//        User authenticatedUser = authenticationService.authenticate(loginUserDto);
//
//        String jwtToken = jwtService.generateToken(authenticatedUser); 
//        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime()); 
//        return ResponseEntity.ok(loginResponse);
//    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUserDto.getUserName(),
                            loginUserDto.getPassword()
                    )
            );

            User authenticatedUser = userRepository.findByUserName(loginUserDto.getUserName())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found")); // More specific exception

            String jwtToken = jwtService.generateToken(authenticatedUser);
            LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
            return ResponseEntity.ok(loginResponse);

        } catch (AuthenticationException e) { // Catch authentication failures
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginError("Invalid username or password",HttpStatus.UNAUTHORIZED)); // Return error response
        }
    }

    record LoginError(String description , HttpStatus status) {
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
//    refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/authen")
    public ResponseEntity<List<User>> authentification(@RequestParam("user") String login, @RequestParam("pass") String password) {
        List<User> dd = authenticationService.findone(login, password);
        return ResponseEntity.ok().body(dd);

    }
/// new

    @PostMapping("/accessUser")
    public ResponseEntity<AccessUserDTO> createUser(@Valid @RequestBody AccessUserDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException, IOException {
        AccessUserDTO result = accessUserService.saves(dTO);
        return ResponseEntity.created(new URI("/api/auth/accessUser/" + result.getId())).body(result);
    }

    @PutMapping("/accessUser/update")
    public ResponseEntity<AccessUserDTO> updateUser(@Valid @RequestBody AccessUserDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException, IOException {

        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        if (dTO.getUserName() == null) {
            bindingResult.addError(new FieldError("AccessUserDTO", "User", "PUT method does not accepte " + ENTITY_NAME + " with code"));
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        AccessUserDTO result = accessUserService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/accessUser/all")
    public ResponseEntity<List<AccessUserDTO>> getAllAccessUserWithOutPasswrod() {
        return ResponseEntity.ok().body(accessUserService.findAllAcessUserWithOutPassword());
    }

    @GetMapping("/accessUser/allWithPass")
    public ResponseEntity<List<AccessUserDTO>> getAllAccessUser() {
        return ResponseEntity.ok().body(accessUserService.findAllAcessUser());
    }

    @GetMapping("/accessUser/{UserName}/sginature")
    public ResponseEntity<Resource> getSignature(@PathVariable Long code) {
        AccessUserDTO dto = accessUserService.findOneByCode(code);

        Resource ressource = new ByteArrayResource(dto.getSignature());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(ressource);
    }

}
