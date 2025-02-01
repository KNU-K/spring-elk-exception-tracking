package com.example.springelkexceptiontracking.service;

import com.example.springelkexceptiontracking.dto.UserDTO;
import com.example.springelkexceptiontracking.entity.User;
import com.example.springelkexceptiontracking.exception.UserNotFoundException;
import com.example.springelkexceptiontracking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    @Override
    public User register(UserDTO request) {
        String email = request.getEmail();
        String name = request.getName();
        String password = request.getPassword();
        User createdUser = User.create(name, email, password);
        return userRepository.save(createdUser);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }
}
