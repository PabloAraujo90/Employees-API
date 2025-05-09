package com.hypertech.employees.DTOs;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
	
	private Long Id;
	
	@NotBlank(message = "First name cannot be empty")  // No permite valores vacíos o nulos
	private String firstName;
	
	private String middleName;
	
	@NotBlank(message = "Last name cannot be empty")
	private String lastName;
	
	private String secondLastName;
    
	@Min(value = 18, message = "Age must be at least 18 years old")
    @Max(value = 65, message = "Age cannot be greater than 65")
	private Integer age;
    
	@NotBlank(message = "Gender cannot be empty")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
	private String gender;
   
	@NotNull(message = "Birth date cannot be null")
    @Past(message = "Birth date must be in the past")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthDate; 
    
	@NotBlank(message = "Position cannot be empty")
	private String position;
}
