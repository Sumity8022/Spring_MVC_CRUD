package com.demos.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageDto;
import org.springframework.stereotype.Service;

import com.demos.dto.MyUserDto;
import com.demos.entities.MyUser;
import com.demos.repositories.MyUserRepo;
import com.demos.services.MyUserService;

@Service
public class MyUserServiceImpl implements MyUserService {
	
	@Autowired
	private ModelMapper modelMapper;

	// inject repo bean

	@Autowired
	private MyUserRepo userRepo;

	@Override
	public MyUserDto saveNewUser(MyUserDto user) {
		
		
		//convert DTO into entity
		MyUser enti = modelMapper.map(user, MyUser.class);
		

		MyUser savedObj = userRepo.save(enti);
		
		//convert entity into DTO
		MyUserDto userDto = modelMapper.map(savedObj, MyUserDto.class);
		

		return userDto;

	}// 1
	
	
	

	@Override
	public List<MyUser> getAllUserData() {
		
		List<MyUser> alluser = userRepo.findAll();
		
		return alluser;
	}




	@Override
	public void deleteUserById(int id) {
		
		userRepo.deleteById(id);
	}




	@Override
	public MyUser editUser(int id) {
	
		Optional<MyUser> opt = userRepo.findById(id);
		
		MyUser myuser=null;
		
		if(opt.isPresent())
		{
			 myuser = opt.get();
		}
		
		
		return myuser;
	}




	@Override
	public MyUser getById(int id) {
		
		Optional<MyUser> opt = userRepo.findById(id);
		
		MyUser user = opt.orElseThrow(()->new RuntimeException("Could Not Found User"));
		
		return user;
		
	
	}




	@Override
	public MyUser updateUser(MyUser existingObj) {
		MyUser existingUserUpdated = userRepo.save(existingObj);
		return existingUserUpdated;
	}




	@Override
	public Page<MyUserDto> findPaginationData(Pageable pageable) {
		
		Page<MyUser> mypages = userRepo.findAll(pageable);
		
		
		//convert entity page object into DTO page object
	      // Convert Page<MyUser> to Page<MyUserDto>
        Page<MyUserDto> pagedto = mypages.map(user -> modelMapper.map(user, MyUserDto.class));
		
		return pagedto;
	}
	
	
	
	
	
	
	
	
	

}//
