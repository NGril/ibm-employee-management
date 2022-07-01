package com.ngril.ibmemployeemanagement.employee.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EmployeeForm(
    @NotBlank @Size(max = 50) String firstName,
    @NotBlank @Size(max = 50) String lastName,
    @NotNull @Past LocalDate dateOfBirth,
    @NotNull EmployeeJobRole jobRole
) {

}
