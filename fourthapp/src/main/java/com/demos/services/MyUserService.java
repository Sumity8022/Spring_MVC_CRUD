package com.demos.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demos.dto.MyUserDto;
import com.demos.entities.MyUser;

public interface MyUserService {

	//for saving user
	public MyUserDto saveNewUser(MyUserDto user);
	
	//to fetch all user data
	public List<MyUser> getAllUserData();
	
	
	//for delete
	public void deleteUserById(int id);
	
	//for edit
	public MyUser editUser(int id);
	
	
	//fetching existing data based on id
	public MyUser getById(int id);
	
	//for update
	public MyUser updateUser(MyUser existingObj);
	
	
	//find based on pagination
	
	public Page<MyUserDto> findPaginationData(Pageable pageable);
	
	
	
}
