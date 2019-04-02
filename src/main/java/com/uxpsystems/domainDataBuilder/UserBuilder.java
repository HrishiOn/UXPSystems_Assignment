/*package com.uxpsystems.domainDataBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.uxpsystems.dao.UserRepository;
import com.uxpsystems.domain.User;

@Component
public class UserBuilder implements ApplicationRunner{

	private UserRepository userRepository;
	
	@Autowired
	public UserBuilder(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {

		if(userRepository.count()==0) {
			
			User firstUser = new User();
			firstUser.setUserName("Andrew");
			firstUser.setPassword("Willson");
			
			User secondUser = new User();
			secondUser.setUserName("Beth");
			secondUser.setPassword("Samson");
			
			userRepository.save(firstUser);
			userRepository.save(secondUser);
		}
	}

	
}
*/