package com.someshop.intershop.service;

import com.someshop.intershop.dto.UserDto;
import com.someshop.intershop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    String create (Map<String, String> from, MultipartFile file) throws IOException;
    void changeRole (User user, String role);
    void delete (User user, User profile);
    void setActive (User profile, Boolean value);
    List<UserDto> findAll ();
    User findById (String id);
    User changeInfo (User profileUser, Map<String, String> form, MultipartFile file);
}
