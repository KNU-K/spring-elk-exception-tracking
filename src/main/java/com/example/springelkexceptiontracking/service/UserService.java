package com.example.springelkexceptiontracking.service;

import com.example.springelkexceptiontracking.dto.UserDTO;
import com.example.springelkexceptiontracking.entity.User;

public interface UserService {
    User register(UserDTO request);

    User findUserById(Long id);
}
