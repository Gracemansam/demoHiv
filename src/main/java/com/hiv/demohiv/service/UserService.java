package com.hiv.demohiv.service;

import com.hiv.demohiv.UserDto;
import com.hiv.demohiv.model.Users;
import com.hiv.demohiv.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

@NoArgsConstructor
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public Users save(UserDto user) {

            Users newUser = new Users();
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            userRepository.save(newUser);
            log.info("User saved successfully: {}", newUser);
            return newUser;
    }
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public Users update(Long id, UserDto user) {
        Optional<Users> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            Users existingUser = existingUserOptional.get();
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            userRepository.save(existingUser);
            log.info("User updated successfully: {}", existingUser);
            return existingUser;
        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            log.info("User deleted successfully with id: {}", id);
        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
    }







}
