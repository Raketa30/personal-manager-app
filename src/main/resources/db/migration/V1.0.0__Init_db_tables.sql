CREATE SCHEMA IF NOT EXISTS employee_management;

ALTER SCHEMA employee_management OWNER TO postgres;

CREATE SEQUENCE hibernate_sequence START 1000 INCREMENT 1;

CREATE TABLE IF NOT EXISTS employee_management."employee"
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    firstname   TEXT                  NOT NULL,
    lastname    TEXT                  NOT NULL,
    salary      INTEGER               NOT NULL,
    position_id INTEGER               NOT NULL
);

CREATE TABLE IF NOT EXISTS employee_management."position"
(
    id            SERIAL PRIMARY KEY NOT NULL,
    title         TEXT               NOT NULL,
    min_salary    INTEGER            NOT NULL,
    max_salary    INTEGER            NOT NULL,
    department_id INTEGER            NOT NULL
);


CREATE TABLE IF NOT EXISTS employee_management."department"
(
    id    SERIAL PRIMARY KEY NOT NULL,
    title TEXT               NOT NULL
);

ALTER TABLE ONLY employee_management."employee"
    ADD CONSTRAINT "employee_position_id_fk" FOREIGN KEY (position_id) REFERENCES employee_management."position" (id);

ALTER TABLE ONLY employee_management."position"
    ADD CONSTRAINT "position_department_id_fk" FOREIGN KEY (department_id) REFERENCES employee_management."department" (id);

INSERT INTO employee_management."department" (title)
VALUES ('CRM'),
       ('HR'),
       ('ERP');

INSERT INTO employee_management."position" (title, min_salary, max_salary, department_id)
VALUES ('MANAGER', 180000, 300000, 1),
       ('JUNIOR_DEVELOPER', 40000, 70000, 1),
       ('MIDDLE_DEVELOPER', 140000, 180000, 1),
       ('TOP_MANAGER', 200000, 250000, 2),
       ('TEAM_LEAD', 200000, 300000, 3);
