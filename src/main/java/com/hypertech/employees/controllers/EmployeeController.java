package com.hypertech.employees.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hypertech.employees.DTOs.EmployeeDTO;
import com.hypertech.employees.services.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@Tag(name = "Employee API", description = "Endpoints para la gestión de empleados")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	/*
	 * METODO PARA OBTENER TODOS LOS EMPLEADOS*/
	@Operation(summary = "Obtener todos los empleados", description = "Devuelve la lista de todos los empleados registrados")
	@GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
	/*
	 * METODO PARA OBTENER UN EMPLEADO POR ID*/
    @Operation(summary = "Obtener un empleado por ID", description = "Busca un empleado por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
    /*
	 * METODO PARA GUARDAR UN EMPLEADO*/
    @Operation(summary = "Crea un empleado", description = "Crea un nuevo registro empleado")
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(employeeDTO));
    }
    /*
	 * METODO PARA UNO O VARIOS EMPLEADOS POR (LOTE)*/
    @Operation(summary = "Crea uno o varios empleados", description = "Crea uno o varios registros en lote de empleados")
    @PostMapping("/batch")
    public ResponseEntity<List<EmployeeDTO>> saveEmployeesBatch (@Valid @RequestBody List<EmployeeDTO> employeeDTOs){
    	return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployeesBatch(employeeDTOs));
    }
    /*
	 * METODO PARA ACTUALIZAR UN EMPLEADO EXISTENTE */
    @Operation(summary = "Actualiza un empleado", description = "Actualiza un registro existente de empleados")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeDTO employeeDTO){
    	EmployeeDTO updatedEmployee = employeeService.updateEmploye(id, employeeDTO);
    	return ResponseEntity.ok(updatedEmployee);
    }
    /*
	 * METODO PARA ELIMINAR UN EMPLEADO*/
    @Operation(summary = "Elimina un empleado por ID", description = "Busca y elimina un empleado por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    /*
	 * METODO PARA ACTUALIZAR UN EMPLEADO EXISTENTE (SIRVE PARA ACTUALIZAR UNA PROPIEDAD)*/
    @Operation(summary = "Actualiza una propiedad de empleado", description = "Actualiza un registro (propiedad) existente de empleados")
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeePatch(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){
    	EmployeeDTO updatedEmployee = employeeService.updateEmployePatch(id, employeeDTO);
    	return ResponseEntity.ok(updatedEmployee);
    }
	
	
}
