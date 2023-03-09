package com.shopp.store.controller;

import com.shopp.store.customException.UserAlreadyExistException;
import com.shopp.store.entity.request.AutenticationRequest;
import com.shopp.store.entity.request.RegisterRequest;
import com.shopp.store.entity.response.AuthenticationResponse;
import com.shopp.store.entity.response.RegisterResponse;
import com.shopp.store.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
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
    public ResponseEntity<AuthenticationResponse> loginAuth(@RequestBody AutenticationRequest authRequest){
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
}
