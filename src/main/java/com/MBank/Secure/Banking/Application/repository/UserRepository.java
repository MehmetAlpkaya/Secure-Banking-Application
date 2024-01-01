package com.MBank.Secure.Banking.Application.repository;

import com.MBank.Secure.Banking.Application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);
}
