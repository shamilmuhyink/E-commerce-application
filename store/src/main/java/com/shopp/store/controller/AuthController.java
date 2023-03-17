package com.shopp.store.controller;

import com.shopp.store.customException.UserAlreadyExistException;
import com.shopp.store.entity.request.AuthenticationRequest;
import com.shopp.store.entity.request.RegisterRequest;
import com.shopp.store.entity.response.AuthenticationResponse;
import com.shopp.store.entity.response.RegisterResponse;
import com.shopp.store.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Post: Register the user

    @PostMapping(path = "/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest regRequest) throws UserAlreadyExistException {
        return ResponseEntity.ok(authService.register(regRequest));
    }

    // Login the user
    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponse> loginAuth(@RequestBody AuthenticationRequest authRequest){
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    @GetMapping()
    public String getHello(){
        return "Hello";
    }

}
