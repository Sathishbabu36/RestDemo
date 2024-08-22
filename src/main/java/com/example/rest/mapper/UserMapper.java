package com.example.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.example.rest.model.User;
import com.example.rest.requestdto.UserRequest;
import com.example.rest.responsedto.UserResponse;

@Component
public class UserMapper {

	public User mapToUserEntity(UserRequest request,User user) {
		user.setUserName(request.getUserName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());

		return user;
	}

	public UserResponse mapToUserResponse(User user) {

		UserResponse response = new UserResponse();
		response.setUserId(user.getUserId());
		response.setUserName(user.getUserName());
		response.setEmail(user.getEmail());

		return response;
	}

}
