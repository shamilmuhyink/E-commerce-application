package com.shopp.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_seq", initialValue = 201,allocationSize = 1)
    private long addressId;
    private String addressLine;
    private String city;
    private String state;
    private int pinCode;
    @ManyToMany(mappedBy = "addresses")
    private Set<AppUser> users = new HashSet<>();
}
