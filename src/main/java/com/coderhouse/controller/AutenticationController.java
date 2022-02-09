package com.coderhouse.controller;


import com.coderhouse.auxiliaries.Jwt.JwtProvider;
import com.coderhouse.handle.ExceptionAutentication;
import com.coderhouse.model.User;
import com.coderhouse.service.AutenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private String loginAutentificatition(@RequestParam("email") String userEmail, @RequestParam("password") String userPassword) {
        String token = jwtProvider.getJWTToken(userEmail, jwtSecret);
        return token;
    }

    @PostMapping(path = "/user")
    private User createNewUser(@RequestBody User user) throws ExceptionAutentication {
        return autenticationService.createNewUser(user);
    }

}
