package com.shopp.store.service;

import com.shopp.store.configuration.JwtService;
import com.shopp.store.customException.UserAlreadyExistException;
import com.shopp.store.entity.Address;
import com.shopp.store.entity.AppUser;
import com.shopp.store.entity.request.AuthenticationRequest;
import com.shopp.store.entity.request.RegisterRequest;
import com.shopp.store.entity.response.AuthenticationResponse;
import com.shopp.store.entity.response.RegisterResponse;
import com.shopp.store.repository.AddressRepository;
import com.shopp.store.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AppUserRepository userDao;
    @Autowired
    private AddressRepository addressDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager  authenticationManager;

    @Autowired
    private JwtService jwtService;

    // Create a method to register the RequestUser(appUser) to the database
    public RegisterResponse register(RegisterRequest regRequest) throws UserAlreadyExistException {
        // check weather the user already exists
        RegisterResponse regResponse = new RegisterResponse();
        Optional<AppUser> user1 = userDao.findByEmail(regRequest.getEmail());
        if(!user1.isEmpty()){
            throw new UserAlreadyExistException("user already exists");
        }
        AppUser user = AppUser.builder()
                .firstName(regRequest.getFirstName())
                .secondName(regRequest.getSecondName())
                .dob(regRequest.getDob())
                .mobileNumber(regRequest.getMobileNumber())
                .email(regRequest.getEmail())
                .password(passwordEncoder.encode(regRequest.getPassword()))
                .addresses(regRequest.getAddresses())
                .role(regRequest.getRole())
                .accountCreated(LocalDate.now())
                .build();
        for(Address address: regRequest.getAddresses()){
            addressDao.save(address);
        }
        userDao.save(user);
        var jwtToken = jwtService.generateToken(user);
        regResponse.setJwtToken(jwtToken);
        return regResponse;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        var user = userDao.findByEmail(authRequest.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setJwtToken(jwtToken);
        return authResponse;
    }
}
