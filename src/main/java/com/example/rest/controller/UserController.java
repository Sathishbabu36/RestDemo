package com.example.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.rest.requestdto.UserRequest;
import com.example.rest.responsedto.UserResponse;
import com.example.rest.service.UserService;
import com.example.rest.util.AppResponseBuilder;
import com.example.rest.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
public class UserController {

	private UserService userService;
	private AppResponseBuilder responseBuilder;

	public UserController(UserService userService, AppResponseBuilder responseBuilder) {
		super();
		this.userService = userService;
		this.responseBuilder=responseBuilder;
	}

	@Operation(description = "The API endpoint is used to save the user to the database. The "
			+ "endpoint will automatically create a unique identifier to the table and returns"
			+ " the user data along with the identifier the end point doesnt return the user "
			+ "password considering the security and privacy",
			responses = {
					@ApiResponse(responseCode = "201", description = "User Created"),
					@ApiResponse(responseCode = "500", description = "Internal Server Error",
					content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class)),
					})
	})
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody @Valid UserRequest userRequest) {
		UserResponse response = userService.saveUser(userRequest);
		return responseBuilder.success(HttpStatus.CREATED,"user created", response);
	}

	@Operation(description = "The API Endpoint is used to find the user based on the unique Identifier."
			+ "The endpoint requires a path variable 'userId' and responds with user data.",
			responses = {
					@ApiResponse(responseCode = "302", description = "User Found"),
					@ApiResponse(responseCode = "404", description = "Failed to Find User",
					content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))		
					})
	})
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable int userId) {
		UserResponse response =userService.findUserById(userId);
		return responseBuilder.success(HttpStatus.FOUND,"user found", response);
	}

	@Operation(description = "The API Endpoint is used update the User Entity based on the unique Identifier. The "
			+ "endpoint requires a path variable 'userId' and responds with user data.",
			responses = {
					@ApiResponse(responseCode = "302",description = "User Updated"),
					@ApiResponse(responseCode = "404",description = "Failed to Update",
					content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
	})
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody UserRequest userRequest,@PathVariable int userId) {
		UserResponse response = userService.updateUser(userRequest,userId);
		return responseBuilder.success(HttpStatus.OK,"user updated", response);
	}

	@Operation(description = "The API Endpoint is used to delete User Entity present inside the database. The"
			+ "Endpoint requires a path variable 'userId' and responds with user data.",
			responses = {
					@ApiResponse(responseCode = "200",description = "User Deleted"),
					@ApiResponse(responseCode = "404",description = "Failed to delete",
					content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
			})
	@DeleteMapping("/users/{userId}")
	public ResponseEntity< ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId) {
		UserResponse response = userService.deleteUser(userId);
		return responseBuilder.success(HttpStatus.OK, "user deleted", response);
	}

	@Operation(description = "The API Endpoint is used to Show all the Users present in users table",
			responses = {
					@ApiResponse(responseCode = "302",description = "Users found"),
					@ApiResponse(responseCode = "404",description = "Failed to found",
					content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
			})
	@GetMapping("users")
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser(){
		List<UserResponse> response = userService.findAllUser();
		return responseBuilder.success(HttpStatus.FOUND, "user list found", response);
	}
}
