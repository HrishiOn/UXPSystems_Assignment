package com.uxpsystems.service;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.uxpsystems.dao.UserRepository;
import com.uxpsystems.domain.User;

public class UserService {

	@Autowired
	UserRepository userRepository;
	
	List<User> userList;
	
	private Logger logger;
	
	public enum UserStatus{
		Activated, Deactivated;
	}
	
	public String deleteIfPresent(long id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			logger.info("User deleted by the Id", id);
			return "User deleted successfully";
		}
		logger.info("No User present for the Id", id);
		return "no User present for given Id";
	}

	public String saveIfPresentInDatabase(@Valid User user) {
		if(isUserExist(user)) {
			userRepository.save(user);
			logger.info("Updating the existing User if it present in DB", user);
			return "User updated successfully";
		}
		return "User does not exist";
	}

	public String createNewUserIfAbsent(User user) {
		if(isUserExist(user)) {
			return "User aleardy exists";
		}
		logger.info("Creating a new User only if it's absent with setting the Status to Activated, and will not update if it there already", user);
		user.setStatus(UserStatus.Activated);
		userRepository.save(user);
		return "User updated successfully";
	}

	public List<User> returnAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	public User returnUserById(Long id) {
		logger.info("Returning the User if it present with given Id", id);
		User user = userRepository.findById(id)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		return user;
	}
	
	public void deleteUser(User user) {
		if(isUserExist(user)) {
			logger.info("Changing the User if it presents with Activated Status");
			user.setStatus(UserStatus.Deactivated);
		}
			logger.info("Deleting the User from the DB");
			userRepository.delete(user);
	}
	
	/**
	 * @param returns User only if he is Activated and he is in the DB
	 * @return
	 */
	private boolean isUserExist(User user) {
		return user.getStatus()==UserStatus.Activated && userRepository.existsById(user.getId());
	}
}
