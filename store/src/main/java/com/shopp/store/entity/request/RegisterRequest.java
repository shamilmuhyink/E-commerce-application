package com.shopp.store.entity.request;

import com.shopp.store.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
