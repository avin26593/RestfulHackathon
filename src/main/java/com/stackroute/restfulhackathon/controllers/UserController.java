package com.stackroute.restfulhackathon.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.restfulhackathon.domain.Users;
import com.stackroute.restfulhackathon.services.UserServiceDef;


@RestController
@RequestMapping("/v1.0/hackathon/users")
public class UserController {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
	        return matcher.find();
	}
	
	public boolean ValidateRequestParams(String emailid,String username) {
		if(username!=null && emailid!=null) {
			return true;
		}
		else return validate(emailid);
	}
	
	@Autowired
	UserServiceDef userservice;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody Users user) {
		String s= "server not responding";
		if(ValidateRequestParams(user.getEmailId(),user.getUsername())==false) {
			s="Invalid usernmae or emailid entered";
			return new ResponseEntity<String>(s, HttpStatus.CONFLICT);
		}
		try {
			s=userservice.AddnewUser(user);
			return new ResponseEntity<String>(s, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			s="connection to server could not be established";
		}
		return new ResponseEntity<String>(s, HttpStatus.CONFLICT);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getAllUsers() {
		List<Users> userlist = new ArrayList<>() ;

		userlist=userservice.getAllUsers();
		return new ResponseEntity<List<Users>>(userlist, HttpStatus.OK) ;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@RequestParam(value="id") int id) {
		String s= "server not responding";
		try {
			s=userservice.deleteUser(id);
			return new ResponseEntity<String>(s, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(s, HttpStatus.CONFLICT);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> updateUserByid(@RequestBody Users user,@RequestParam(value="id") int id){
		String s= "server not responding";
		user.setId(id);
		try {
			s=userservice.updateUser(user,id);
			return new ResponseEntity<String>(s, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(s, HttpStatus.CONFLICT);
	}
}