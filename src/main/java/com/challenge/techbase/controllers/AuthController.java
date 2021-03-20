package com.challenge.techbase.controllers;

import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.models.dto.LoginRequest;
import com.challenge.techbase.models.dto.ReqData;
import com.challenge.techbase.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @PostMapping("/login")
    public ResponseEntity<ReqData> login(@Valid @RequestBody LoginRequest loginRequest) {
        ReqData reqData = new ReqData();
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (user == null) {
            reqData.setCode(400);
            reqData.setMessage("login ngu");
        }

        return ResponseEntity.ok(reqData);

    }


    @GetMapping("/test")
    public String test() {
        session.setAttribute("user", "techbase");
        return "test";
    }
}
