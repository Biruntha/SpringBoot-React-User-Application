package com.biruntha.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.biruntha.demo.model.User;

public interface UserDao extends MongoRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
