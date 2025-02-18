package com.hypertech.employees.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hypertech.employees.DTOs.EmployeeDTO;
import com.hypertech.employees.entities.Empleado;

@Configuration
public class ModelMapperConfig {
	@Bean
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		// Mapeo personalizado: Empleado -> EmployeeDTO
        modelMapper.typeMap(Empleado.class, EmployeeDTO.class)
                .addMapping(Empleado::getPrimerNombre, EmployeeDTO::setFirstName)
                .addMapping(Empleado::getSegundoNombre, EmployeeDTO::setMiddleName)
                .addMapping(Empleado::getApellidoPaterno, EmployeeDTO::setLastName)
                .addMapping(Empleado::getApellidoMaterno, EmployeeDTO::setSecondLastName)
                .addMapping(Empleado::getEdad, EmployeeDTO::setAge)
                .addMapping(Empleado::getSexo, EmployeeDTO::setGender)
                .addMapping(Empleado::getFechaDeNacimiento, EmployeeDTO::setBirthDate)
                .addMapping(Empleado::getPuesto, EmployeeDTO::setPosition);

        // Mapeo inverso: EmployeeDTO -> Empleado
        modelMapper.typeMap(EmployeeDTO.class, Empleado.class)
                .addMapping(EmployeeDTO::getFirstName, Empleado::setPrimerNombre)
                .addMapping(EmployeeDTO::getMiddleName, Empleado::setSegundoNombre)
                .addMapping(EmployeeDTO::getLastName, Empleado::setApellidoPaterno)
                .addMapping(EmployeeDTO::getSecondLastName, Empleado::setApellidoMaterno)
                .addMapping(EmployeeDTO::getAge, Empleado::setEdad)
                .addMapping(EmployeeDTO::getGender, Empleado::setSexo)
                .addMapping(EmployeeDTO::getBirthDate, Empleado::setFechaDeNacimiento)
                .addMapping(EmployeeDTO::getPosition, Empleado::setPuesto);

        return modelMapper;
    }
		

}
