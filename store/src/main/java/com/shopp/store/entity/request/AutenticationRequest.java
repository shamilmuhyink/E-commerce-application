package com.shopp.store.entity.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutenticationRequest {
    private String email;
    private String password;
}
