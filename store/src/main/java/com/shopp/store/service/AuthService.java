package com.shopp.store.service;

import com.shopp.store.customException.UserAlreadyExistException;
import com.shopp.store.dto.UserDTO;
import com.shopp.store.request.RegisterRequest;
import com.shopp.store.response.AuthenticationResponse;
import com.shopp.store.response.RegisterResponse;

public interface AuthService {
    //CRUD Operations
    public RegisterResponse register(RegisterRequest registerRequest) throws UserAlreadyExistException;
    public AuthenticationResponse getUserById(long userId);
    public UserDTO updateUser(long userId, UserDTO userEwquest);
    public String deleteUser(long userId);

}
