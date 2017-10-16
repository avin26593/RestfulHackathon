package com.stackroute.restfulhackathon.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.restfulhackathon.domain.Users;
import com.stackroute.restfulhackathon.repositories.UserRepo;
@Service
public class UserService implements UserServiceDef{
	@Autowired
	UserRepo userdao;

	public String AddnewUser(Users user) {
		userdao.save(user);
		return "User Sucessfully added";
	}

	public List<Users> getAllUsers() {
		List<Users> data= new ArrayList<Users>();
		data.addAll( (Collection<? extends Users>) userdao.findAll());
		return data;
	}

	public String deleteUser(int id) {
		userdao.delete(id);
		return null;
	}

	public String getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	public String updateUser(Users user, int id) {
		userdao.save(user);
		return null;
	}

}
