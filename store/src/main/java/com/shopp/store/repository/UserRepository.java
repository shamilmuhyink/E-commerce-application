package com.shopp.store.repository;

import com.shopp.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //SQL query: "SELECT * FROM app_user WHERE email="shamilmuhyin@gmail.com""
    Optional<User> findByEmail(String email);
}
