package com.demos.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyUserDto {
	
private int uid;
	
    @Size(min=3,max=8,message = "invalid name max 8 chars allowed and min 3 chars!!!")
	private String fName;
    
    @Size(min=3,max=8,message = "invalid name max 8 chars allowed and min 3 chars!!!" )
	private String lname;
    
    @NotNull(message = "invalid emails!!!")
	private String email;
    
    @Min(10)
	private long mob;
	
	@Size(min=8,max=8,message = "invalid password max and min 8 chars allowed!!!")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^\\w\\s]).{8,}$",message = "at least one uppercase letter, one lowercase letter, one digit, one special character, and a minimum length of 8 characters")
	private String password;
	
	
	
	

}
