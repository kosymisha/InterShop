package com.someshop.intershop.service;

import com.someshop.intershop.dto.UserDto;
import com.someshop.intershop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService extends UserDetailsService {
    String create (User user, String role, MultipartFile file) throws IOException;
    void changeRole (User user, String role);
    void delete (User user);
    List<UserDto> findAll ();
    User findById (String id);
}
