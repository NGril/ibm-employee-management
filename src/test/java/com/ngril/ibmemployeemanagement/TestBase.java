package com.ngril.ibmemployeemanagement;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureTestDatabase
@SpringBootTest(classes = Application.class)
public abstract class TestBase {
}
