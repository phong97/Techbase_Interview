package com.challenge.techbase.controllers;

import com.challenge.techbase.exceptions.RestException;
import com.challenge.techbase.models.dto.req.LoginRequest;
import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.models.security.JwtAuthenticationResponse;
import com.challenge.techbase.services.UserService;
import com.challenge.techbase.util.Enum.Status;
import com.challenge.techbase.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@Valid @RequestBody LoginRequest req) {
        Optional<User> userOptional = this.userService.findByEmail(req.getEmail());
        if (!userOptional.isPresent()) {
            logger.error("User not found with email [{}].", req.getEmail());
            throw new RestException("User not found");
        }

        User user = userOptional.get();
        if (Status.DELETED.equals(user.getStatus())) {
            logger.error("User is not active");
            throw new RestException("User is not active");
        }

        if (!this.userService.checkLogin(user, req.getPassword())) {
            logger.error("Wrong email or password.");
            throw new RestException("Wrong email or password.");
        }
        String token = this.jwtUtils.generateJwtToken(user);
        JwtAuthenticationResponse res = new JwtAuthenticationResponse(token);
        return ResponseEntity.ok(res);
    }
}
