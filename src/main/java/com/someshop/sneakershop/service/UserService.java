package com.someshop.sneakershop.service;

import com.someshop.sneakershop.model.Role;
import com.someshop.sneakershop.model.User;
import com.someshop.sneakershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s);
    }

    public String create (User user, String role) {
        User userFromDb = userRepository.findByEmail(user.getEmail());
        if(userFromDb != null)
            return "User exists";
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.valueOf(role)));
        userRepository.save(user);
        return "Success";
    }

    public void changeRole (User user, String role) {
        user.getRoles().clear();
        user.getRoles().add(Role.valueOf(role));
        userRepository.save(user);
    }

    public void delete (User user) {
        userRepository.delete(user);
    }
}
