CREATE TABLE IF NOT EXISTS appointments (
    id BIGSERIAL PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL, appointment_type VARCHAR(64) NOT NULL, preferred_date VARCHAR(64) NOT NULL
);
