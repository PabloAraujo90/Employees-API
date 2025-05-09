package com.hypertech.employees.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypertech.employees.DTOs.EmployeeDTO;
import com.hypertech.employees.entities.Empleado;
@Service
public class EmployeeMapper {
	@Autowired
	private ModelMapper modelMapper;

	
	public EmployeeDTO toDTO(Empleado empleado) {
	        return modelMapper.map(empleado, EmployeeDTO.class);
	    }

    public Empleado toEntity(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, Empleado.class);
    }
	    
}
