package com.ngril.ibmemployeemanagement.employee.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employees")
public class EmployeeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(value = AccessLevel.NONE)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "date_of_birth")
  @DateTimeFormat(pattern = "yyyy-dd-MM")
  private LocalDate dateOfBirth;

  @Column(name = "job_role")
  @Enumerated(EnumType.STRING)
  private EmployeeJobRole jobRole;
}
