package com.hypertech.employees.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hypertech.employees.entities.Empleado;
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

}
