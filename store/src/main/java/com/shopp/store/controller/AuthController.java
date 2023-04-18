package com.shopp.store.controller;

import com.shopp.store.customException.UserAlreadyExistException;
import com.shopp.store.request.AuthenticationRequest;
import com.shopp.store.request.RegisterRequest;
import com.shopp.store.response.AuthenticationResponse;
import com.shopp.store.response.RegisterResponse;
import com.shopp.store.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "localhost:4200")
public class AuthController {
    @Autowired
    private AuthService authService;

    // Post: Register the user
    @PostMapping(path = "/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest regRequest) throws UserAlreadyExistException {
        return ResponseEntity.ok().body(authService.register(regRequest));
    }

    @PostMapping(path="/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authRequest){
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        String error = ex.getMessage();
        return ResponseEntity.badRequest().body(error);
    }

}
