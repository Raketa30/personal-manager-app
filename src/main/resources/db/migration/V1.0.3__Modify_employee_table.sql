ALTER TABLE employee_management."employee"
    ADD uuid TEXT;

CREATE UNIQUE INDEX employee_uuid_index
    ON employee_management."employee" (uuid);