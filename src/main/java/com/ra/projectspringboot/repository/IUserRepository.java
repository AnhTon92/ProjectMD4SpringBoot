package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}