//package com.hiv.demohiv.controller;
//
//import com.hiv.demohiv.RequirePermission;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/user/plugin")
//public class UserController {
//
//    @RequirePermission("USER")
//    @GetMapping("/user-data")
//    public ResponseEntity<String> getUserData() {
//        return ResponseEntity.ok("This is protected user data from the plugin");
//    }
//}
