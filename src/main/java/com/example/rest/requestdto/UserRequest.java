package com.example.rest.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRequest {
    
	@NotNull(message = "UserName cannot be Null")
	private String userName;
	@NotNull(message = "UserName cannot be Null")
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail.com$",
	            message="The Email can accept only Alphabets,Numerics and %+- symbols, it should be end with @gmail.com")
	private String email;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$",message="Password should be accepted only there should be atleast one Uppercase"
			+ ",special characters,Numeric value and it shoud be 8 characters")
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
