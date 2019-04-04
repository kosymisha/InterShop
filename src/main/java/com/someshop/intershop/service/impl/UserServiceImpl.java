package com.someshop.intershop.service.impl;

import com.someshop.intershop.model.Role;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.UserRepository;
import com.someshop.intershop.service.FileService;
import com.someshop.intershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileServiceImpl fileService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s);
    }

    public String create (User user, String role, MultipartFile file) throws IOException {
        User userFromDb = userRepository.findByEmail(user.getEmail());
        if(userFromDb != null)
            return "User exists";
        user.setActive(true);
        user.setPhotoURL(fileService.uploadToS3(file));
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
