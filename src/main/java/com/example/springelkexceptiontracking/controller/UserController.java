package com.example.springelkexceptiontracking.controller;

import com.example.springelkexceptiontracking.dto.UserDTO;
import com.example.springelkexceptiontracking.entity.User;
import com.example.springelkexceptiontracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("")
    ResponseEntity<User> register(@RequestBody UserDTO request){
        return ResponseEntity.ok(userService.register(request));
    }
    @GetMapping("/{user_id}")
    ResponseEntity<User> findById(@PathVariable(name="user_id") Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }
}
