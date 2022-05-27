CREATE TABLE IF NOT EXISTS employee_management."task"
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    uuid        TEXT                  NOT NULL,
    description TEXT                  NOT NULL,
    employee_id BIGINT                NOT NULL
);

ALTER TABLE ONLY employee_management."task"
    ADD CONSTRAINT "task_employee_id_fk" FOREIGN KEY (employee_id) REFERENCES employee_management."employee" (id);

ALTER TABLE ONLY employee_management."position"
    ADD max_task_size INTEGER DEFAULT 5;