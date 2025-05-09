package com.hypertech.employees.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypertech.employees.DTOs.EmployeeDTO;
import com.hypertech.employees.entities.Empleado;
import com.hypertech.employees.exceptions.EmployeeNotFoundException;
import com.hypertech.employees.repositories.EmpleadoRepository;
import com.hypertech.employees.utils.BeanCopyUtils;
import com.hypertech.employees.utils.EmployeeMapper;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private BeanCopyUtils beanCopyUtils;

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		log.info("Fetching all employees");
		return empleadoRepository.findAll().stream().map(employeeMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO getEmployeeById(Long id) {
		log.info("Fetching employee with id {}",id);
		Empleado empleado = empleadoRepository.findById(id).orElseThrow(() -> {
			log.error("Employee with id {} not found", id); // Log de error
			return new EmployeeNotFoundException("Employee not found with id: " + id);
		});
		return employeeMapper.toDTO(empleado);
	}

	@Override
	public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
		log.info("Saving new employee: {}", employeeDTO.getFirstName()); 
		Empleado empleado = employeeMapper.toEntity(employeeDTO);
		Empleado savedEmpleado = empleadoRepository.save(empleado);
		log.info("Employee {} saved successfully with id {}", savedEmpleado.getPrimerNombre(), savedEmpleado.getEmpleadoId());
		return employeeMapper.toDTO(savedEmpleado);
		
		
	}

	@Override
	public void deleteEmployee(Long id) {
		log.info("Attempting to delete employee with id {}", id); 
		if(!empleadoRepository.existsById(id)) {
			log.error("Employee with id {} not found", id); // Log de error
			throw new EmployeeNotFoundException("Employee not found with Id: " + id);
		}
		empleadoRepository.deleteById(id);
		log.info("Employee with id {} deleted successfully", id);
	}

	@Override
	public EmployeeDTO updateEmploye(Long id, EmployeeDTO employeeDTO) {
		log.info("Attempting to update employee with id {}", id);
		Empleado empleadoExistente = empleadoRepository.findById(id).orElseThrow(()-> {
			log.error("Employee with id {} not found", id);
			return new EmployeeNotFoundException("Employee not found with Id: " + id);
		});
		
		 // Actualizamos los campos permitidos
        empleadoExistente.setPrimerNombre(employeeDTO.getFirstName());
        empleadoExistente.setSegundoNombre(employeeDTO.getMiddleName());
        empleadoExistente.setApellidoPaterno(employeeDTO.getLastName());
        empleadoExistente.setApellidoMaterno(employeeDTO.getSecondLastName());
        empleadoExistente.setEdad(employeeDTO.getAge());
        empleadoExistente.setSexo(employeeDTO.getGender());
        empleadoExistente.setFechaDeNacimiento(employeeDTO.getBirthDate());
        empleadoExistente.setPuesto(employeeDTO.getPosition());

		Empleado EmpleadoActualizado = empleadoRepository.save(empleadoExistente);
		log.info("Employee with id {} updated successfully", id);
		return employeeMapper.toDTO(EmpleadoActualizado);
	}

	@Override
	public List<EmployeeDTO> saveEmployeesBatch(List<EmployeeDTO> employeeDTOs) {
		log.info("Saving batch of {} employees", employeeDTOs.size()); 

	    List<Empleado> empleados = employeeDTOs.stream()
	            .map(employeeMapper::toEntity)
	            .collect(Collectors.toList());

	    List<Empleado> savedEmployees = empleadoRepository.saveAll(empleados);
	    
	    log.info("Batch of {} employees saved successfully", savedEmployees.size()); // Log después de guardar
	    
	    return savedEmployees.stream()
	            .map(employeeMapper::toDTO)
	            .collect(Collectors.toList());
	}


	@Override
	public EmployeeDTO updateEmployePatch(Long id, EmployeeDTO employeeDTO) {
		log.info("Attempting to update employee with id {}", id);
		Empleado empleadoExistente = empleadoRepository.findById(id).orElseThrow(()-> {
			log.error("Employee with id {} not found", id);
			return new EmployeeNotFoundException("Employee not found with Id: " + id);
		});
		
		Empleado empleadoUpdate = employeeMapper.toEntity(employeeDTO);
	
		BeanCopyUtils.copyNonNullProperties(empleadoUpdate, empleadoExistente);
		
		Empleado EmpleadoActualizado = empleadoRepository.save(empleadoExistente);
		log.info("Employee with id {} updated successfully", id);
		return employeeMapper.toDTO(EmpleadoActualizado);
	}
	
	

}
