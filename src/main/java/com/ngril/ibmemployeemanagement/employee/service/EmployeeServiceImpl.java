package com.ngril.ibmemployeemanagement.employee.service;

import com.ngril.ibmemployeemanagement.employee.model.EmployeeDTO;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeEntity;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeForm;
import com.ngril.ibmemployeemanagement.employee.repository.EmployeeRepository;
import com.ngril.ibmemployeemanagement.employee.util.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Override
  public List<EmployeeDTO> findAllEmployees() {
    return employeeRepository.findAll().stream().map(EmployeeMapper::mapToDTO).toList();
  }

  @Override
  public Optional<EmployeeDTO> findEmployeeById(Long id) {
    return employeeRepository.findById(id).map(EmployeeMapper::mapToDTO);
  }

  @Override
  public Optional<EmployeeDTO> addEmployee(EmployeeForm form) {
    EmployeeEntity entity = employeeRepository.save(EmployeeMapper.mapToEntity(form));
    return Optional.of(EmployeeMapper.mapToDTO(entity));
  }

  @Override
  public Optional<EmployeeDTO> updateEmployee(Long id, EmployeeForm form) {
    Optional<EmployeeEntity> updatedEntity =
        employeeRepository.findById(id).map(employeeEntity -> updateEmployeeData(id, form));
    return updatedEntity.map(employeeRepository::save).map(EmployeeMapper::mapToDTO);
  }

  private EmployeeEntity updateEmployeeData(Long id, EmployeeForm form) {
    return new EmployeeEntity(
        id, form.firstName(), form.lastName(), form.dateOfBirth(), form.jobRole());
  }
}
