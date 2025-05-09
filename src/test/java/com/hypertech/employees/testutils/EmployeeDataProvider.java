package com.hypertech.employees.testutils;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.hypertech.employees.DTOs.EmployeeDTO;
import com.hypertech.employees.entities.Empleado;

public class EmployeeDataProvider {
	/*
	 * SET DE DATOS DE PRUEBA DTOs
	 * */
	public static List<EmployeeDTO> provideEmployeeDTOs (){
		System.out.println("DATA PROVIDER TEST");
		return List.of(
				new EmployeeDTO(1L, "Pablo", null, "Mendoza", "Araujo", 34, "Male", localDateToDate(LocalDate.of(1990, 12, 28)), "Developer"),
                new EmployeeDTO(2L, "Ana", "María", "Rodríguez", "Fernández", 28, "Female", localDateToDate(LocalDate.of(1995, 8, 22)), "QA Engineer")
				);
	}
	/*
	 * SET DE DATOS DE PRUEBA ENTITY
	 * */
    public static List<Empleado> provideEmpleados() {
        return List.of(
        		new Empleado(1L, "Pablo", null, "Mendoza", "Araujo", 34, "Male", localDateToDate(LocalDate.of(1990, 12, 28)), "Developer"),
                new Empleado(2L, "Ana", "María", "Rodríguez", "Fernández", 28, "Female", localDateToDate(LocalDate.of(1995, 8, 22)), "QA Engineer")
        );
    }
	/*
	 * METODO PARA CONVERTIR LOCALDATE A DATE
	 * @param localDate Fecha a convertir
	 * */
	public static Date localDateToDate(LocalDate localDate) {
	        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    }
	/*
	 * METODO PARA CONVERTIR ENTITY A DTO y facilitar las pruebas
	 * @param empleado Objeto empleado a convertir a DTO
	 * */
	public static EmployeeDTO convertToDTO(Empleado empleado) {
		    return new EmployeeDTO(
		            empleado.getEmpleadoId(),
		            empleado.getPrimerNombre(),
		            empleado.getSegundoNombre(),
		            empleado.getApellidoPaterno(),
		            empleado.getApellidoMaterno(),
		            empleado.getEdad(),
		            empleado.getSexo(),
		            empleado.getFechaDeNacimiento(),
		            empleado.getPuesto()
		    );
		}


}
