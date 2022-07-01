package com.ngril.ibmemployeemanagement.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngril.ibmemployeemanagement.TestBase;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeDTO;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeForm;
import com.ngril.ibmemployeemanagement.employee.model.EmployeeJobRole;
import com.ngril.ibmemployeemanagement.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class EmployeeControllerTest extends TestBase {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private EmployeeService employeeService;

  @Test
  void shouldFindAllEmployees() throws Exception {
    // given
    var givenEmployee1 =
        new EmployeeDTO(
            1L, "John", "Doe", LocalDate.of(2000, 1, 1), EmployeeJobRole.SOFTWARE_DEVELOPER);
    var givenEmployee2 =
        new EmployeeDTO(
            1L, "John", "Doe", LocalDate.of(2000, 1, 1), EmployeeJobRole.SOFTWARE_DEVELOPER);
    var givenEmployees = List.of(givenEmployee1, givenEmployee2);

    given(employeeService.findAllEmployees()).willReturn(givenEmployees);

    // when
    var actual = mockMvc.perform(get("/employees"));

    // then
    var result =
        actual
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

    var responseBody = result.getResponse().getContentAsString();
    var expectedJson = objectMapper.writer().writeValueAsString(givenEmployees);
    then(responseBody).isEqualTo(expectedJson);
  }

  @Test
  void shouldFindEmployeeById() throws Exception {
    // given
    var givenId = 1L;
    var givenEmployee =
        new EmployeeDTO(
            givenId, "John", "Doe", LocalDate.of(2000, 1, 1), EmployeeJobRole.SOFTWARE_DEVELOPER);

    given(employeeService.findEmployeeById(givenId)).willReturn(Optional.of(givenEmployee));

    // when
    var actual = mockMvc.perform(get("/employees/" + givenId));

    // then
    var result =
        actual
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

    var responseBody = result.getResponse().getContentAsString();
    var expectedJson = objectMapper.writer().writeValueAsString(givenEmployee);
    then(responseBody).isEqualTo(expectedJson);
  }

  @Test
  void shouldAddEmployee() throws Exception {
    // given
    var givenId = 1L;
    var givenEmployeeForm =
        new EmployeeForm(
            "John", "Doe", LocalDate.of(2000, 1, 1), EmployeeJobRole.SOFTWARE_DEVELOPER);
    var givenEmployeeDTO =
        new EmployeeDTO(
            givenId, "John", "Doe", LocalDate.of(2000, 1, 1), EmployeeJobRole.SOFTWARE_DEVELOPER);

    var givenRequestBody = objectMapper.writer().writeValueAsString(givenEmployeeForm);

    given(employeeService.addEmployee(givenEmployeeForm)).willReturn(Optional.of(givenEmployeeDTO));

    // when
    var actual =
        mockMvc.perform(
            post("/employees").contentType(MediaType.APPLICATION_JSON).content(givenRequestBody));

    // then
    var result =
        actual
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

    var responseBody = result.getResponse().getContentAsString();
    var expectedJson = objectMapper.writer().writeValueAsString(givenEmployeeDTO);
    then(responseBody).isEqualTo(expectedJson);
  }

  @Test
  void shouldUpdateEmployee() throws Exception {
    // given
    var givenId = 1L;
    var givenEmployeeForm =
        new EmployeeForm(
            "Mia", "Wallace", LocalDate.of(1999, 2, 2), EmployeeJobRole.PROJECT_MANAGER);
    var givenEmployeeDTO =
        new EmployeeDTO(
            givenId,
            "Mia",
            "Wallace",
            LocalDate.of(1999, 2, 2),
            EmployeeJobRole.SOFTWARE_DEVELOPER);

    var givenRequestBody = objectMapper.writer().writeValueAsString(givenEmployeeForm);

    given(employeeService.updateEmployee(givenId, givenEmployeeForm))
        .willReturn(Optional.of(givenEmployeeDTO));

    // when
    var actual =
        mockMvc.perform(
            put("/employees/" + givenId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(givenRequestBody));

    // then
    var result =
        actual
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

    var responseBody = result.getResponse().getContentAsString();
    var expectedJson = objectMapper.writer().writeValueAsString(givenEmployeeDTO);
    then(responseBody).isEqualTo(expectedJson);
  }
}
