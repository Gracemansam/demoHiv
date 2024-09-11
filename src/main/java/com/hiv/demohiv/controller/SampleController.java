package com.hiv.demohiv.controller;

import com.hiv.demohiv.UserDto;
import com.hiv.demohiv.model.Users;
import com.hiv.demohiv.repository.UserRepository;
import com.hiv.demohiv.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController {

    private final UserService userService;

//    @Autowired
//    public SampleController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello from plugin";
    }
@Transactional
    @PostMapping("/save")
    public Users save(@RequestBody UserDto user) {

        return userService.save(user);
    }

@GetMapping("/{id}")
public ResponseEntity<Users> getUserById(@PathVariable Long id) {
    Optional<Users> user = userService.findById(id);
    return user.map(ResponseEntity::ok)
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
}

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            Users updatedUser = userService.update(id, userDto);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
