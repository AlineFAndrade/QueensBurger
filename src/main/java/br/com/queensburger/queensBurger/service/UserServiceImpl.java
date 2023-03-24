package br.com.queensburger.queensBurger.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.queensburger.queensBurger.model.User;
import br.com.queensburger.queensBurger.repository.UserRepository;

	@Service
	public class UserServiceImpl implements UserService , UserDetailsService{
  
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
  
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		Optional<User> optUser = repository.findByEmail(email);
		if(optUser.isPresent()) {
			return optUser.get();
		} else {
			throw new UsernameNotFoundException("Username não encontrado");
		}
	}

	@Override
	public User include(User user) {
		Optional<User> userWithSameEmail = repository.findByEmail(user.getEmail());
		
		if(userWithSameEmail.isEmpty()) {
			return repository.save(user); 
		} else {
			return null;
		}
	}

	@Override
	public User update(Integer id, User user) throws duplicatedProductException {
		Optional<User> resultFromDB = repository.findById(id);
		
		if(resultFromDB.isPresent()) {
			User userFromDB = resultFromDB.get();
			
			String emailFounded = user.getEmail();
			Optional<User> userWithSameEmail = repository.findByEmail(emailFounded);
			
			if(userWithSameEmail != null && userWithSameEmail.get().getId() != userFromDB.getId()) {
				throw new duplicatedProductException();
			}
			
			userFromDB.setId(user.getId());
			userFromDB.setName(user.getName());
			userFromDB.setEmail(user.getEmail());
			userFromDB.setPassword(user.getPassword());
			userFromDB.setRole(user.getRole());
					
			return repository.save(userFromDB);
		} else {
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		Optional<User> resultFromDB = repository.findByEmail(email);
	
		if(resultFromDB.isPresent()) {
			return resultFromDB.get();
		} else {
			return null;
		}
	}
  
}

