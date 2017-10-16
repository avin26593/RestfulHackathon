package com.stackroute.restfulhackathon.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.stackroute.restfulhackathon.domain.Users;

@Repository
public interface UserRepo extends CrudRepository<Users, Integer>{

}
