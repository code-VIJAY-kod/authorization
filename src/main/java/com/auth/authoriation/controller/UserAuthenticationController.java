package com.auth.authoriation.controller;

import com.auth.authoriation.model.AuthenticationData;
import com.auth.authoriation.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class UserAuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping(value = "/welcome")
    public ResponseEntity<String> welcome(){
        return new ResponseEntity<>("welcome", HttpStatus.OK);
    }
    @PostMapping(value = "/auth")
    public String authenticateUSer(@RequestBody AuthenticationData authenticationData){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationData.getUserName(), authenticationData.getPassword()
            ));
        }catch (Exception e){
            System.out.println("Invalid credentials..");
        }
        return jwtUtil.generateToken(authenticationData.getUserName());
    }
}
