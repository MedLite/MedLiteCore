/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DevPointSystem.MedLite.Authentification.service;

/**
 *
 * @author Administrator
 */
//import com.DevPointSystem.Comptabilite.Authentification.Config.JwtTokenUtil;
import com.DevPointSystem.MedLite.Authentification.domaine.User;
import com.DevPointSystem.MedLite.Authentification.dto.LoginUserDto;
import com.DevPointSystem.MedLite.Authentification.dto.RegisterUserDto;
import com.DevPointSystem.MedLite.Authentification.repository.UserRepository;
import com.DevPointSystem.MedLite.web.errors.IllegalBusinessLogiqueException;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

//    @Autowired
//    private JwtService jwtUtil;
    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {

        Optional<User> domaine = userRepository.findByUserName(input.getUserName());
        if (domaine.isPresent()) {
//         new ErrorResponse("Username already exists: " + input.getUserName()); // Custom exception
            throw new IllegalBusinessLogiqueException("Username.already.exists");
        }
        User user = new User()
                .setFullName(input.getFullName())
                .setUserName(input.getUserName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()));
        user.setPasswordDecry(input.getPassword());

        return userRepository.save(user);
    }

    record ErrorResponse(String message) {

    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUserName(),
                        input.getPassword()
                )
        );

        return userRepository.findByUserName(input.getUserName())
                .orElseThrow();

    }

    record LoginResponse(String token, long expiresIn) {

    }

    record LoginError(String message) {

    }

    public List<User> findone(String user, String passwd) {
        List<User> accesscont = userRepository.findByUserNameAndPassword(user, passwd);
        return accesscont;
    }

}
