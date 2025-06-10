package com.pdstpo.unomas.repositories;

import com.pdstpo.unomas.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
