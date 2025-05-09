package com.hypertech.employees.exceptions;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {
	
	public EmployeeNotFoundException(String message) {
        super(message);
    }
}
