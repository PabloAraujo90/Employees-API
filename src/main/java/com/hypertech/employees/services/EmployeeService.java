package com.hypertech.employees.services;

import java.util.List;

import com.hypertech.employees.DTOs.EmployeeDTO;

/*
 * INTERFAZ DE SERVICIO*/
public interface EmployeeService {
	
	    List<EmployeeDTO> getAllEmployees();
	    EmployeeDTO getEmployeeById(Long id);
	    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
	    EmployeeDTO updateEmploye(Long id, EmployeeDTO employeeDTO);
	    EmployeeDTO updateEmployePatch(Long id, EmployeeDTO employeeDTO);
	    List<EmployeeDTO> saveEmployeesBatch (List<EmployeeDTO> employeeDTOs);
	    void deleteEmployee(Long id);


}
