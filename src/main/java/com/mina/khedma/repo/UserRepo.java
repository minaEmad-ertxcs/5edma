package com.mina.khedma.repo;

import com.mina.khedma.DAO.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserDAO, Integer> {
    UserDAO findByUsername(String username);
}