package com.coderhouse.controller;


import com.coderhouse.auxiliaries.Jwt.JwtProvider;
import com.coderhouse.handle.ExceptionAutentification;
import com.coderhouse.model.Producto;
import com.coderhouse.model.User;
import com.coderhouse.model.UserSimplify;
import com.coderhouse.service.AutentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "")
public class AutentificationController {

    private String jwtSecret = "springbootjwtcoderhouse";

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AutentificationService autentificationService;

    @PostMapping(path = "/login")
    private String loginAutentificatition(@RequestParam("email") String userEmail, @RequestParam("password") String userPassword) {
        String token = jwtProvider.getJWTToken(userEmail, jwtSecret);
        return token;
    }

    @PostMapping(path = "/user")
    private User createNewUser(@RequestBody User user) throws ExceptionAutentification {
        return autentificationService.createNewUser(user);
    }

}
