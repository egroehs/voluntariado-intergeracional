package com.afetoconecta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.afetoconecta.models.User;
import com.afetoconecta.models.UserType;
import com.afetoconecta.services.UserService;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("type/{userType}")
    public List<User> getUsersByType(@PathVariable UserType userType) {
    return userService.getUsersByType(userType);

    }


}
    
    
    