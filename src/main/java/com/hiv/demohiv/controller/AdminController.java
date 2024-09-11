//package com.hiv.demohiv.controller;
//
//import com.hiv.demohiv.RequirePermission;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/admin/plugin")
//public class AdminController {
//
//    @RequirePermission("ADMIN")
//    @GetMapping("/admin-data")
//    public ResponseEntity<String> getAdminData() {
//        return ResponseEntity.ok("This is protected admin data from the plugin");
//    }
//
//}
