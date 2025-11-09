package com.mina.khedma.service;

import com.mina.khedma.DAO.UserDAO;
import com.mina.khedma.model.UserPrincipal;
import com.mina.khedma.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO userDAO = userRepo.findByUsername(username);
        if (userDAO == null) {
            throw new UsernameNotFoundException("User is not found");
        }

        return new UserPrincipal(userDAO);
    }
}