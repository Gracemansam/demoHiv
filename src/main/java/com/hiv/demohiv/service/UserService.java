package com.hiv.demohiv.service;

import com.hiv.demohiv.UserDto;
import com.hiv.demohiv.model.Users;
import com.hiv.demohiv.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public Users save(UserDto user) {
        try {
            Users newUser = new Users();
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            try{
           // Users savedUser = userRepository.save(newUser);
            userRepository.customSave(user.getName(), user.getEmail(), user.getPassword());
            } catch (DataAccessException e) {
                // Handle the exception here
                throw new RuntimeException("Error executing custom save", e);
            }
            log.info("User saved successfully: {}", newUser);
            return newUser;
        } catch (Exception e) {
            log.error("Error saving user: {}", e.getMessage());
            throw e; // Rethrow the exception to propagate it
        }
    }
}
