package com.hypertech.employees.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hypertech.employees.DTOs.EmployeeDTO;
import com.hypertech.employees.entities.Empleado;
import com.hypertech.employees.exceptions.EmployeeNotFoundException;
import com.hypertech.employees.repositories.EmpleadoRepository;
import com.hypertech.employees.testutils.EmployeeDataProvider;
import com.hypertech.employees.utils.EmployeeMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
	@Mock
    private EmpleadoRepository empleadoRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    
    private List<Empleado> empleados;
    private List<EmployeeDTO> employeeDTOs;
	
    @BeforeEach
    void setUp() {
        empleados = EmployeeDataProvider.provideEmpleados();
        employeeDTOs = EmployeeDataProvider.provideEmployeeDTOs();
    }
    
	@Test
    void testGetAllEmployees() {
		// GIVEN
	    List<Empleado> empleados = EmployeeDataProvider.provideEmpleados(); // Obtenemos la lista de entidades
	    when(empleadoRepository.findAll()).thenReturn(empleados);

	    // Convertimos la lista de empleados a DTOs usando `employeeMapper.toDTO()`
	    List<EmployeeDTO> expectedDTOs = empleados.stream()
	            .map(emp -> {
	                EmployeeDTO dto = EmployeeDataProvider.convertToDTO(emp);
	                when(employeeMapper.toDTO(emp)).thenReturn(dto); // Mapeamos correctamente
	                return dto;
	            })
	            .toList(); 

	    // WHEN
	    List<EmployeeDTO> result = employeeService.getAllEmployees();

	    // THEN
	    assertNotNull(result);
	    assertEquals(expectedDTOs.size(), result.size());

	    // Log para verificar los datos convertidos
	    log.info("Empleados Convertidos a DTO:");
	    log.info(result.toString());   
    }

	@Test
	void testGetEmployeeById() {
			    
		// GIVEN
	    Empleado empleado = empleados.get(0);
	    EmployeeDTO expectedDTO = EmployeeDataProvider.convertToDTO(empleado);
        
	    //WHEN
	    when(empleadoRepository.findById(empleado.getEmpleadoId())).thenReturn(Optional.of(empleado));
	    when(employeeMapper.toDTO(empleado)).thenReturn(expectedDTO); // Conversión mas clara
	 
	    EmployeeDTO result = employeeService.getEmployeeById(empleado.getEmpleadoId());

	    // THEN
	    assertNotNull(result);
	    assertEquals(expectedDTO.getFirstName(), result.getFirstName());
	    verify(empleadoRepository, times(1)).findById(empleado.getEmpleadoId());
	}

	@Test
    void testGetEmployeeById_WhenNotExists() {
        // GIVEN
        Long invalidId = 99L;
        
        //WHEN
        when(empleadoRepository.findById(invalidId)).thenReturn(Optional.empty());

        // THEN
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(invalidId));
    }
	
	@Test
    void testSaveEmployee() {
        // GIVEN
        EmployeeDTO employeeDTO = employeeDTOs.get(0);
        Empleado empleado = empleados.get(0);

        when(employeeMapper.toEntity(employeeDTO)).thenReturn(empleado);
        when(empleadoRepository.save(empleado)).thenReturn(empleado);
        when(employeeMapper.toDTO(empleado)).thenReturn(employeeDTO);

        // WHEN
        EmployeeDTO result = employeeService.saveEmployee(employeeDTO);

        // THEN
        assertNotNull(result);
        assertEquals(employeeDTO.getFirstName(), result.getFirstName());
        verify(empleadoRepository, times(1)).save(empleado);
    }


    @Test
    void testDeleteEmployee() {
        // GIVEN
        Long employeeId = empleados.get(0).getEmpleadoId();
        when(empleadoRepository.existsById(employeeId)).thenReturn(true);

        // WHEN
        employeeService.deleteEmployee(employeeId);

        // THEN
        verify(empleadoRepository, times(1)).deleteById(employeeId);
    }

    @Test
    void testDeleteEmployee_WhenNotExists() {
        // GIVEN
        Long invalidId = 999L;
        when(empleadoRepository.existsById(invalidId)).thenReturn(false);

        // THEN
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(invalidId));
    }
    


    @Test
    void testUpdateEmployee() {
        // GIVEN
        Long employeeId = empleados.get(0).getEmpleadoId();
        EmployeeDTO updatedDTO = new EmployeeDTO(employeeId, "NuevoNombre", null, "Mendoza", "Araujo", 34, "Male", null, "Manager");
        Empleado existingEmpleado = empleados.get(0);

        when(empleadoRepository.findById(employeeId)).thenReturn(Optional.of(existingEmpleado));
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(existingEmpleado);
        when(employeeMapper.toDTO(any(Empleado.class))).thenReturn(updatedDTO);

        // WHEN
        EmployeeDTO result = employeeService.updateEmploye(employeeId, updatedDTO);

        // THEN
        assertNotNull(result);
        assertEquals(updatedDTO.getFirstName(), result.getFirstName());
        assertEquals(updatedDTO.getPosition(), result.getPosition());
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }

    @Test
    void testUpdateEmployee_WhenNotExists() {
        // GIVEN
        Long invalidId = 999L;
        EmployeeDTO employeeDTO = employeeDTOs.get(0);

        when(empleadoRepository.findById(invalidId)).thenReturn(Optional.empty());

        // THEN
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateEmploye(invalidId, employeeDTO));
    }
    
    @Test
    void testSaveEmployeesBatch() {
        // GIVEN
        when(employeeMapper.toEntity(any(EmployeeDTO.class)))
                .thenAnswer(invocation -> {
                    EmployeeDTO dto = invocation.getArgument(0);
                    return new Empleado(dto.getId(), dto.getFirstName(), dto.getMiddleName(), dto.getLastName(), 
                                        dto.getSecondLastName(), dto.getAge(), dto.getGender(), dto.getBirthDate(), dto.getPosition());
                });

        when(empleadoRepository.saveAll(anyList())).thenReturn(empleados);
        when(employeeMapper.toDTO(any(Empleado.class))).thenAnswer(invocation -> {
            Empleado emp = invocation.getArgument(0);
            return new EmployeeDTO(emp.getEmpleadoId(), emp.getPrimerNombre(), emp.getSegundoNombre(),
                                   emp.getApellidoPaterno(), emp.getApellidoMaterno(), emp.getEdad(), emp.getSexo(),
                                   emp.getFechaDeNacimiento(), emp.getPuesto());
        });

        // WHEN
        List<EmployeeDTO> result = employeeService.saveEmployeesBatch(employeeDTOs);

        // THEN
        assertNotNull(result);
        assertEquals(employeeDTOs.size(), result.size());
        verify(empleadoRepository, times(1)).saveAll(anyList());
    }



}
