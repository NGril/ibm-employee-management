CREATE TABLE IF NOT EXISTS employees (
    id IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    job_role VARCHAR(70) NOT NULL
);