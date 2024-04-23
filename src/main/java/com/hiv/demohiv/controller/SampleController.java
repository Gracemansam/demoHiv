package com.hiv.demohiv.controller;

import com.hiv.demohiv.UserDto;
import com.hiv.demohiv.model.Users;
import com.hiv.demohiv.repository.UserRepository;
import com.hiv.demohiv.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/save")
    public Users save(@RequestBody UserDto user) {
        return userService.save(user);
    }
//    @GetMapping("/get-all-user")
//    public Iterable<Users> getAllUser() {
//        return userRepository.findAll();
//    }


}
