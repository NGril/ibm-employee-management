package com.ngril.ibmemployeemanagement.employee.model;

import java.time.LocalDate;

public record EmployeeDTO(
    Long id,
    String firstName,
    String lastName,
    LocalDate dateOfBirth,
    EmployeeJobRole jobRole
) {
}
