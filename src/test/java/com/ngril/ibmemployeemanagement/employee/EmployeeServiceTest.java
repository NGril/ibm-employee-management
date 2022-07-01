package com.ngril.ibmemployeemanagement.employee;

import com.ngril.ibmemployeemanagement.TestBase;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeDTO;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeEntity;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeForm;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeJobRole;
import com.ngril.ibmemployeemanagement.employee.repository.EmployeeRepository;
import com.ngril.ibmemployeemanagement.employee.service.EmployeeService;
import com.ngril.ibmemployeemanagement.employee.util.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

public class EmployeeServiceTest extends TestBase {

  @MockBean private EmployeeRepository employeeRepository;

  @Autowired private EmployeeService employeeService;

  @Test
  void shouldFindAll() {
    // given
    var givenEmployee1 =
        new EmployeeEntity(
            1L, "John", "Doe", LocalDate.of(2000, 1, 1), EmployeeJobRole.SOFTWARE_DEVELOPER);
    var givenEmployee2 =
        new EmployeeEntity(
            2L, "Mia", "Wallace", LocalDate.of(1999, 2, 2), EmployeeJobRole.PROJECT_MANAGER);
    var givenEmployees = List.of(givenEmployee1, givenEmployee2);

    given(employeeRepository.findAll()).willReturn(givenEmployees);

    // when
    var actual = employeeService.findAllEmployees();

    // then
    var expectedEmployee1 =
        new EmployeeDTO(
            1L, "John", "Doe", LocalDate.of(2000, 1, 1), EmployeeJobRole.SOFTWARE_DEVELOPER);
    var expectedEmployee2 =
        new EmployeeDTO(
            2L, "Mia", "Wallace", LocalDate.of(1999, 2, 2), EmployeeJobRole.PROJECT_MANAGER);
    var expected = List.of(expectedEmployee1, expectedEmployee2);

    then(actual).isEqualTo(expected);
  }

  @Test
  void shouldReturnEmptyListWhenNoEmployeesFound() {
    // given
    var givenEmployees = List.<EmployeeEntity>of();
    given(employeeRepository.findAll()).willReturn(givenEmployees);

    // when
    var actual = employeeService.findAllEmployees();

    // then
    then(actual).isEmpty();
  }

  @Test
  void shouldFindEmployeeById() {
    // given
    var givenId = 1L;
    var givenEmployee =
        Optional.of(
            new EmployeeEntity(
                1L, "John", "Doe", LocalDate.of(2000, 1, 1), EmployeeJobRole.SOFTWARE_DEVELOPER));

    given(employeeRepository.findById(givenId)).willReturn(givenEmployee);

    // when
    var actual = employeeService.findEmployeeById(givenId);

    // then
    var expected =
        Optional.of(
            new EmployeeDTO(
                1L, "John", "Doe", LocalDate.of(2000, 1, 1), EmployeeJobRole.SOFTWARE_DEVELOPER));
    then(actual).isEqualTo(expected);
  }

  @Test
  void shouldReturnEmptyOptionalIfEmployeeNotFound() {
    // given
    var givenId = 1L;
    var givenEmployee = Optional.<EmployeeEntity>empty();
    given(employeeRepository.findById(givenId)).willReturn(givenEmployee);

    // when
    var actual = employeeService.findEmployeeById(givenId);

    // then
    then(actual).isEmpty();
  }

  @Test
  void shouldAddEmployee() {
    // given
    var givenEmployeeEntity =
        new EmployeeEntity(
            1L, "John", "Doe", LocalDate.of(2000, 1, 1), EmployeeJobRole.SOFTWARE_DEVELOPER);
    var givenEmployeeForm =
        new EmployeeForm(
            "John", "Doe", LocalDate.of(2000, 1, 1), EmployeeJobRole.SOFTWARE_DEVELOPER);
    var givenEmployeeDTO =
        new EmployeeDTO(
            1L, "John", "Doe", LocalDate.of(2000, 1, 1), EmployeeJobRole.SOFTWARE_DEVELOPER);

    given(employeeRepository.save(any(EmployeeEntity.class))).willReturn(givenEmployeeEntity);

    // when
    var actual = employeeService.addEmployee(givenEmployeeForm);

    // then`
    var expected = Optional.of(givenEmployeeDTO);
    then(actual).isEqualTo(expected);
  }

  @Test
  void shouldUpdateEmployee() {
    // given
    var givenId = 1L;
    var givenEmployeeEntity =
        new EmployeeEntity(
            givenId, "John", "Doe", LocalDate.of(2000, 1, 1), EmployeeJobRole.SOFTWARE_DEVELOPER);

    var givenUpdatedEmployeeEntity =
        new EmployeeEntity(
            givenId, "Mia", "Wallace", LocalDate.of(1999, 2, 2), EmployeeJobRole.PROJECT_MANAGER);

    var givenEmployeeUpdateForm =
        new EmployeeForm(
            "Mia", "Wallace", LocalDate.of(1999, 2, 2), EmployeeJobRole.PROJECT_MANAGER);

    given(employeeRepository.findById(givenId)).willReturn(Optional.of(givenEmployeeEntity));
    given(employeeRepository.save(any(EmployeeEntity.class)))
        .willReturn(givenUpdatedEmployeeEntity);

    // when
    var actual = employeeService.updateEmployee(givenId, givenEmployeeUpdateForm);

    // then
    var expected =
        Optional.of(
            new EmployeeDTO(
                givenId,
                "Mia",
                "Wallace",
                LocalDate.of(1999, 2, 2),
                EmployeeJobRole.PROJECT_MANAGER));
    then(actual).isEqualTo(expected);
  }
}
