package com.ngril.ibmemployeemanagement.employee.controller;

import com.ngril.ibmemployeemanagement.employee.service.EmployeeService;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeDTO;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeForm;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping
  List<EmployeeDTO> findAllEmployees() {
    return employeeService.findAllEmployees();
  }

  @GetMapping("/{id}")
  ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable final Long id) {
    return employeeService
        .findEmployeeById(id)
        .map(employeeDTO -> ResponseEntity.status(HttpStatus.OK).body(employeeDTO))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @PostMapping
  ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody final EmployeeForm form) {
    return employeeService
        .addEmployee(form)
        .map(employeeDTO -> ResponseEntity.status(HttpStatus.CREATED).body(employeeDTO))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }

  @PutMapping("/{id}")
  ResponseEntity<EmployeeDTO> updateEmployee(
      @PathVariable final Long id, @Valid @RequestBody final EmployeeForm form) {
    return employeeService
        .updateEmployee(id, form)
        .map(employeeDTO -> ResponseEntity.status(HttpStatus.OK).body(employeeDTO))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }
}
