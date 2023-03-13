package com.shopp.store.entity.request;

import com.shopp.store.entity.Address;
import com.shopp.store.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public class RegisterRequest {
    private String firstName;
    private String secondName;
    private LocalDate dob;
    private String mobileNumber;
    private String email;
    private String password;
    private Set<Address> addresses = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private Role role;
}
