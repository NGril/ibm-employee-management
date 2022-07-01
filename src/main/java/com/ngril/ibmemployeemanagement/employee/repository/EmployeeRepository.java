package com.ngril.ibmemployeemanagement.employee.repository;

import com.ngril.ibmemployeemanagement.employee.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {}
