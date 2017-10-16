package com.stackroute.restfulhackathon.services;

import java.util.List;

import com.stackroute.restfulhackathon.domain.Users;

public interface UserServiceDef {

	String AddnewUser(Users user);
	List<Users> getAllUsers();
	String deleteUser(int id);
	String getUserById(int id);	
	String updateUser(Users user,int id);


}
