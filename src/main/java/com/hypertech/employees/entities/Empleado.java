package com.hypertech.employees.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empleadoId;
	private String primerNombre;
	private String segundoNombre;
	private String apellidoPaterno;	
	private String apellidoMaterno;	
	private Integer edad;	
	private String Sexo; 	
	private Date fechaDeNacimiento;   	
	private String puesto;
}
