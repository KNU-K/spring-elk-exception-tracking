package com.example.springelkexceptiontracking.repository;

import com.example.springelkexceptiontracking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
