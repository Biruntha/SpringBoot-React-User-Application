package com.biruntha.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biruntha.demo.model.User;
import com.biruntha.demo.repository.UserDao;
import com.biruntha.demo.repository.UserRepositoryCustom;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRepositoryCustom userRepositoryCustom;
	
	public ResponseEntity<User> createUser(User user) {
		try {
			Integer id = userRepositoryCustom.getMaxUserId() + 1;
			User userNew = userDao.save(new User(id, user.getFirstName(), user.getLastName(), 
					user.getUsername(), user.getPassword(), user.getSalary(), user.getAge()));
		    return new ResponseEntity<>(userNew, HttpStatus.CREATED);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<List<User>> listUsers() {
		try {
		    List<User> users = new ArrayList<User>();
		    userDao.findAll().forEach(users::add);
		    if (users.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<User> getUserById(Integer id) {
		Optional<User> user = userDao.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<User> updateUser(Integer id, User user) {
		Optional<User> userData = userDao.findById(id);

		if (userData.isPresent()) {
			User userOld = userData.get();
			userOld.setFirstName(user.getFirstName());
			userOld.setLastName(user.getLastName());
			userOld.setSalary(user.getSalary());
			userOld.setAge(user.getAge());
		    return new ResponseEntity<>(userDao.save(userOld), HttpStatus.OK);
		} else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<HttpStatus> deleteUser(int id) {
		Optional<User> user = userDao.findById(id);
		if (user.isPresent()) {
			userDao.delete(user.get());
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<User> getUserByUsername(String userName) {
		Optional<User> user = userDao.findByUsername(userName);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
