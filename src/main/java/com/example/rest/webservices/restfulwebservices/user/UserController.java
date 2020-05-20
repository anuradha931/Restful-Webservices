package com.example.rest.webservices.restfulwebservices.user;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {
	
	@Autowired
	private UserDAOService userDAOService;
	
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		return userDAOService.findAll();
		 
	}
	
	
	@GetMapping("/users/{id}")
	@ApiOperation(value = "Finds Users by id",
    notes = "Also returns a link to retrieve all users with rel - all-users")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = userDAOService.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+ id);
		
		
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retriveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		//HATEOAS
		
		return resource;
	}
	
	
	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User saveduser=userDAOService.save(user);
		
		//to send the status
		URI location=ServletUriComponentsBuilder.
							fromCurrentRequest().
							path("/{id}").
							buildAndExpand(saveduser.getId()).
							toUri();
		
		return ResponseEntity.created(location).build();		
	}
	
	@DeleteMapping("/users/{id}")
	public void DeleteUsers(@PathVariable int id){
		User user=userDAOService.deleteUser(id);
		if(user==null) {
			throw new UserNotFoundException("id-"+id);
		}
		 
	}
	
	
}
