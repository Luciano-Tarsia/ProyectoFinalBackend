package com.coderhouse.controller;

import com.coderhouse.auxiliaries.jwt.JwtProvider;
import com.coderhouse.handle.ExceptionAutentication;
import com.coderhouse.model.User;
import com.coderhouse.service.AutenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "")
public class AutenticationController {

    private String jwtSecret = "springbootjwtcoderhouse";

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AutenticationService autenticationService;

    @PostMapping(path = "/login")
    private String loginAutentificatition(@RequestParam("email") String userEmail, @RequestParam("password") String userPassword) throws ResponseStatusException {
        try {
            return autenticationService.login(userEmail, userPassword);
        }catch (ExceptionAutentication exceptionAutentication){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionAutentication.getMessage());
        }
    }

    @PostMapping(path = "/user")
    private User createNewUser(@RequestBody User user) throws ResponseStatusException, ExceptionAutentication {
        try {
            return autenticationService.createNewUser(user);
        } catch (ExceptionAutentication exceptionAutentication) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionAutentication.getMessage());
        }
    }
}