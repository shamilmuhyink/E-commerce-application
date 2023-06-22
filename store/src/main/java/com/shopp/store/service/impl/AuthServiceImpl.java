package com.shopp.store.service.impl;

import com.shopp.store.JWT.JwtService;
import com.shopp.store.customException.UserAlreadyExistException;
import com.shopp.store.dto.UserDTO;
import com.shopp.store.entity.User;
import com.shopp.store.request.AuthenticationRequest;
import com.shopp.store.request.RegisterRequest;
import com.shopp.store.response.AuthenticationResponse;
import com.shopp.store.repository.UserRepository;
import com.shopp.store.response.RegisterResponse;
import com.shopp.store.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userDAO;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws UserAlreadyExistException {

        // check weather user already exist, if exist throw exception
//        User existingUser = userDAO.findByEmail(registerRequest.getEmail()).orElseThrow(() -> new UserAlreadyExistException("user Exist"));
        Optional<User> existingUser = userDAO.findByEmail(registerRequest.getEmail());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistException("user Already Exist");
        }
        else{
            // Map registerRequest body to user instance
            User user = User.builder()
                    .firstName(registerRequest.getFirstName())
                    .lastName(registerRequest.getLastName())
                    .dob(registerRequest.getDob())
                    .mobileNumber(registerRequest.getMobileNumber())
                    .email(registerRequest.getEmail())
                    // encode Password
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .addresses(registerRequest.getAddresses())
                    .role(registerRequest.getRole())
                    .accountCreated(LocalDateTime.now())
                    .build();

            // save to database
            user = userDAO.save(user);
            // generate JWT
            var jwToken = jwtService.generateToken(user);
            // produce registerResponse
            RegisterResponse registerResponse = new RegisterResponse();
            registerResponse.setJwtToken(jwToken);
            return registerResponse;
        }
    }

    @Override
    public AuthenticationResponse getUserById(long userId) {
        return null;
    }

    @Override
    public UserDTO updateUser(long userId, UserDTO userEwquest) {
        return null;
    }

    @Override
    public String deleteUser(long userId) {
        return null;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        User user = userDAO.findByEmail(authRequest.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setJwtToken(jwtToken);
        return authResponse;
    }
}
