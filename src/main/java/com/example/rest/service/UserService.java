package com.example.rest.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.rest.exceptions.UserNotFoundByIdException;
import com.example.rest.mapper.UserMapper;
import com.example.rest.model.User;
import com.example.rest.repository.UserRepo;
import com.example.rest.requestdto.UserRequest;
import com.example.rest.responsedto.UserResponse;

@Service
public class UserService {

	private UserRepo userRepo;
	private UserMapper userMapper;

	public UserService(UserRepo userRepo,UserMapper userMapper) {
		super();
		this.userRepo = userRepo;
		this.userMapper=userMapper;
	}

	public UserResponse saveUser(UserRequest userRequest) {
		User user = userMapper.mapToUserEntity(userRequest,new User());
		userRepo.save(user);
		return userMapper.mapToUserResponse(user);
	}

	public UserResponse findUserById(int userId) {
		return userRepo.findById(userId)
				.map(user -> userMapper.mapToUserResponse(user))
				.orElseThrow(()-> new UserNotFoundByIdException("failed to find the user"));	 
	}

	public UserResponse updateUser(UserRequest userRequest, int userId) {
		return userRepo.findById(userId)
				.map(exUser ->{
					userMapper.mapToUserEntity(userRequest, exUser);
					exUser = userRepo.save(exUser);
					return userMapper.mapToUserResponse(exUser);
				}).orElseThrow(() -> new UserNotFoundByIdException("failed to update User"));
	}
	public UserResponse deleteUser(int userId) {
		//		Optional<User> optional = userRepo.findById(userId);
		//		if(optional.isPresent()) {
		//			User user = optional.get();
		//			userRepo.delete(user);
		//			return userMapper.mapToUserResponse(user); 
		//		}
		//		else
		//			throw new UserNotFoundByIdException("Failed to delete user");

		return userRepo.findById(userId)
				.map(exUser ->{
					userRepo.delete(exUser);
					return userMapper.mapToUserResponse(exUser);
				})
				.orElseThrow(()->new UserNotFoundByIdException("failed to delete User"));
	}

	public List<UserResponse> findAllUser() {
		return userRepo.findAll()
				.stream()
				.filter(user -> user.getUserName() !=null)
				.map(user -> userMapper.mapToUserResponse(user))
				.toList();
	}
	
	public UserResponse findByEmail(String email) {
		return userRepo.findByEmail(email)
		.map(user -> userMapper.mapToUserResponse(user))
		.orElseThrow(()->new UserNotFoundByIdException("user failed to found by email"));
	}

	public UserResponse findByEmailAndPassword(String email,String password) {
		return userRepo.findByEmailAndPassword(email, password)
		.map(user->userMapper.mapToUserResponse(user))
		.orElseThrow(()->new UserNotFoundByIdException("user failed to found by email and name"));
	}
}
