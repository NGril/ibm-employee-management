package com.ngril.ibmemployeemanagement.employee.service;

import com.ngril.ibmemployeemanagement.employee.model.EmployeeDTO;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeForm;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

  List<EmployeeDTO> findAllEmployees();

  Optional<EmployeeDTO> findEmployeeById(Long id);

  Optional<EmployeeDTO> addEmployee(EmployeeForm employeeForm);

  Optional<EmployeeDTO> updateEmployee(Long id, EmployeeForm employeeForm);
}
