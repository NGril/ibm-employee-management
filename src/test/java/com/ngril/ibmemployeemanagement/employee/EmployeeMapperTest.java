package com.ngril.ibmemployeemanagement.employee;

import com.ngril.ibmemployeemanagement.employee.model.EmployeeDTO;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeEntity;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeForm;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeJobRole;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.ngril.ibmemployeemanagement.employee.util.EmployeeMapper.mapToDTO;
import static com.ngril.ibmemployeemanagement.employee.util.EmployeeMapper.mapToEntity;
import static org.assertj.core.api.BDDAssertions.then;

public class EmployeeMapperTest {

  @Test
  void shouldMapEmployeeEntityToEmployeeDto() {
    // given
    var givenId = 1L;
    var givenFirstName = "John";
    var givenLastName = "Doe";
    var givenDateOfBirth = LocalDate.of(2000, 1, 1);
    var givenJobRole = EmployeeJobRole.SOFTWARE_DEVELOPER;

    var givenEmployeeEntity =
        new EmployeeEntity(givenId, givenFirstName, givenLastName, givenDateOfBirth, givenJobRole);

    // when
    var actual = mapToDTO(givenEmployeeEntity);

    // then
    var expected =
        new EmployeeDTO(givenId, givenFirstName, givenLastName, givenDateOfBirth, givenJobRole);

    then(actual).isEqualTo(expected);
  }

  @Test
  void shouldMapEmployeeFormToEmployeeEntity() {
    // given
    var givenId = 1L;
    var givenFirstName = "John";
    var givenLastName = "Doe";
    var givenDateOfBirth = LocalDate.of(2000, 1, 1);
    var givenJobRole = EmployeeJobRole.SOFTWARE_DEVELOPER;

    var givenEmployeeForm =
        new EmployeeForm(givenFirstName, givenLastName, givenDateOfBirth, givenJobRole);

    // when
    var actual = mapToEntity(givenEmployeeForm);

    // then
    var expected =
        new EmployeeEntity(givenId, givenFirstName, givenLastName, givenDateOfBirth, givenJobRole);
    then(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
  }
}
