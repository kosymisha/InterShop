package com.someshop.intershop.service.impl;

import com.someshop.intershop.dto.UserDto;
import com.someshop.intershop.model.BankCard;
import com.someshop.intershop.model.Role;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.UserRepository;
import com.someshop.intershop.service.FileService;
import com.someshop.intershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileService fileService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s);
    }

    public String create (Map<String, String> form, MultipartFile file) throws IOException {
        if(userRepository.findByEmail(form.get("email")) != null)
            return "User exists";
        User user = new User(form.get("firstName"),
                form.get("lastName"),
                form.get("email"),
                form.get("password"),
                true,
                Collections.singleton(Role.valueOf(form.get("role"))),
                fileService.uploadToS3(file));
        BankCard bankCard = new BankCard(form.get("numberCard"),
                form.get("firstNameCard").toUpperCase(),
                form.get("lastNameCard").toUpperCase(),
                form.get("monthCard"),
                form.get("yearCard"));
        user.setCard(bankCard);
        userRepository.save(user);
        return "Success";
    }

    public void changeRole (User user, String role) {
        user.getRoles().clear();
        user.getRoles().add(Role.valueOf(role));
        userRepository.save(user);
    }

    public void delete (User user, User profile) {
        if (user.getId().equals(profile.getId())) {
            userRepository.delete(profile);
        }
    }

    @Override
    public void setActive(User profile, Boolean value) {
        profile.setActive(value);
        userRepository.save(profile);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> userDtos = new LinkedList<>();
        for (User user : userRepository.findAll()) {
            userDtos.add(new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getPhotoURL()));
        }
        return userDtos;
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id);
    }
}
