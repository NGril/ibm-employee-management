package com.ngril.ibmemployeemanagement.employee.util;

import com.ngril.ibmemployeemanagement.employee.model.EmployeeDTO;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeEntity;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeForm;

public class EmployeeMapper {

  public static EmployeeDTO mapToDTO(EmployeeEntity entity) {
    return new EmployeeDTO(
        entity.getId(),
        entity.getFirstName(),
        entity.getLastName(),
        entity.getDateOfBirth(),
        entity.getJobRole());
  }

  public static EmployeeEntity mapToEntity(EmployeeForm form) {
    EmployeeEntity entity = new EmployeeEntity();
    entity.setFirstName(form.firstName());
    entity.setLastName(form.lastName());
    entity.setDateOfBirth(form.dateOfBirth());
    entity.setJobRole(form.jobRole());
    return entity;
  }
}
