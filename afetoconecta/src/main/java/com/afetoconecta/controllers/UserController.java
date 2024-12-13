package com.afetoconecta.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afetoconecta.dtos.RegisterDTO;
import com.afetoconecta.models.Meeting;
import com.afetoconecta.models.User;
import com.afetoconecta.models.UserType;
import com.afetoconecta.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDTO registerDTO) {
        User user = userService.registerUser(registerDTO);
    
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/voluntario")
    public List<User> getVoluntarios(@RequestParam(required = false) String localidade) {
        if (localidade != null && !localidade.isEmpty()) {
            return userService.getUsersByTypeAndLocalidade(UserType.VOLUNTARIO, localidade);
        }
        return userService.getUsersByType(UserType.VOLUNTARIO);
    }
    
    @GetMapping("/usuario")
    public List<User> getUsuario(){
        return userService.getUsersByType(UserType.USUARIO);
    }
    
    @GetMapping("/{id}/history")
    public ResponseEntity<Set<Meeting>> getUserMeetingHistory(@PathVariable Long id) {
        Set<Meeting> meetings = userService.getUserMeetingHistory(id);
        return ResponseEntity.ok(meetings);
    }
}