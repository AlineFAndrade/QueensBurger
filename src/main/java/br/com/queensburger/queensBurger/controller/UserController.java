package br.com.queensburger.queensBurger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.queensburger.queensBurger.model.User;
import br.com.queensburger.queensBurger.service.UserService;
import br.com.queensburger.queensBurger.service.duplicatedProductException;

@CrossOrigin
@RestController
public class UserController {
  
  @Autowired
  private UserService service;

  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public ResponseEntity<Object> Post(@RequestBody User user) {
    User registeredUser = service.include(user);

    if(registeredUser != null) {
      return new ResponseEntity<Object>(registeredUser, HttpStatus.OK);
    } else {
      return new ResponseEntity<Object>("Usuário já cadastrado", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/user/{emailUrl}", method = RequestMethod.PUT)
  public ResponseEntity<Object> Put(@PathVariable(value = "emailUrl") int id, 
                                    @RequestBody User newUser) {
    try {
      User updatedUser = service.update(id, newUser);
      if (updatedUser != null) {
        return new ResponseEntity<Object>(updatedUser, HttpStatus.OK);
      } else {
        return new ResponseEntity<Object>("Usuário não encontrado", HttpStatus.BAD_REQUEST);
      }
    } catch ( duplicatedProductException e) {
      e.printStackTrace();
      return new ResponseEntity<Object>("Usuário já cadastrado", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/user/{emailUrl}", method = RequestMethod.GET)
	public ResponseEntity<User> Get(@PathVariable(value = "emailUrl") String email){
		User registeredUser = service.findByEmail(email);
		if(registeredUser != null) {
			return new ResponseEntity<User>(registeredUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}


