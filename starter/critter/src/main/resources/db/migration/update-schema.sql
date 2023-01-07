CREATE TABLE customer
(
    id           BIGINT NOT NULL,
    name         NVARCHAR(500) NULL,
    phone_number VARCHAR(255) NULL,
    notes        VARCHAR(5000) NULL,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE employee
(
    id   BIGINT NOT NULL,
    name NVARCHAR(500) NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id)
);

CREATE TABLE employee_days_available
(
    employee_id    BIGINT NOT NULL,
    days_available INT NULL
);

CREATE TABLE employee_skills
(
    employee_id BIGINT NOT NULL,
    skills      INT NULL
);

CREATE TABLE pet
(
    id          BIGINT NOT NULL,
    type        INT NULL,
    name        VARCHAR(255) NULL,
    customer_id BIGINT NULL,
    birth_date  date NULL,
    notes       VARCHAR(5000) NULL,
    CONSTRAINT pk_pet PRIMARY KEY (id)
);

CREATE TABLE schedule
(
    id   BIGINT NOT NULL,
    date date NULL,
    CONSTRAINT pk_schedule PRIMARY KEY (id)
);

CREATE TABLE schedule_activities
(
    schedule_id BIGINT NOT NULL,
    activities  INT NULL
);

CREATE TABLE schedule_employees
(
    schedule_id  BIGINT NOT NULL,
    employees_id BIGINT NOT NULL
);

CREATE TABLE schedule_pets
(
    schedule_id BIGINT NOT NULL,
    pets_id     BIGINT NOT NULL
);

ALTER TABLE pet
    ADD CONSTRAINT FK_PET_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE employee_days_available
    ADD CONSTRAINT fk_employee_daysavailable_on_employee FOREIGN KEY (employee_id) REFERENCES employee (id);

ALTER TABLE employee_skills
    ADD CONSTRAINT fk_employee_skills_on_employee FOREIGN KEY (employee_id) REFERENCES employee (id);

ALTER TABLE schedule_activities
    ADD CONSTRAINT fk_schedule_activities_on_schedule FOREIGN KEY (schedule_id) REFERENCES schedule (id);

ALTER TABLE schedule_employees
    ADD CONSTRAINT fk_schemp_on_employee FOREIGN KEY (employees_id) REFERENCES employee (id);

ALTER TABLE schedule_employees
    ADD CONSTRAINT fk_schemp_on_schedule FOREIGN KEY (schedule_id) REFERENCES schedule (id);

ALTER TABLE schedule_pets
    ADD CONSTRAINT fk_schpet_on_pet FOREIGN KEY (pets_id) REFERENCES pet (id);

ALTER TABLE schedule_pets
    ADD CONSTRAINT fk_schpet_on_schedule FOREIGN KEY (schedule_id) REFERENCES schedule (id);