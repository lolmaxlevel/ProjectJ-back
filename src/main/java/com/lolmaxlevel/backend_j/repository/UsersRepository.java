package com.lolmaxlevel.backend_j.repository;

import com.lolmaxlevel.backend_j.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
