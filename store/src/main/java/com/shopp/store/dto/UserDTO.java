package com.shopp.store.dto;

import com.shopp.store.entity.Address;
import com.shopp.store.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
public class UserDTO {
    private long userId;
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
