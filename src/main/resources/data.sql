-- Hospital Management System - Test Data
-- =====================================

-- Note: Este archivo contiene datos de prueba
-- En un entorno real, esto sería manejado por la capa de persistencia

-- Sample Patients Data (for reference)
/*
INSERT INTO patients (patient_id, first_name, last_name, document_number, birth_date, phone, email) VALUES
('PAT_001', 'Juan Carlos', 'Pérez González', '12345678', '1990-05-15', '555-1234', 'juan.perez@email.com'),
('PAT_002', 'María Elena', 'Rodríguez López', '23456789', '1985-08-22', '555-2345', 'maria.rodriguez@email.com'),
('PAT_003', 'Carlos Alberto', 'Gómez Martínez', '34567890', '1992-12-03', '555-3456', 'carlos.gomez@email.com');
*/

-- Sample Doctors Data (for reference)
/*
INSERT INTO doctors (doctor_id, first_name, last_name, document_number, specialty, phone, email) VALUES
('DOC_001', 'Dr. Ana María', 'González Herrera', '87654321', 'CARDIOLOGY', '555-5678', 'ana.gonzalez@hospital.com'),
('DOC_002', 'Dr. Roberto', 'Martínez Silva', '76543210', 'NEUROLOGY', '555-6789', 'roberto.martinez@hospital.com'),
('DOC_003', 'Dra. Patricia', 'López Vargas', '65432109', 'PEDIATRICS', '555-7890', 'patricia.lopez@hospital.com');
*/

-- Sample Appointments Data (for reference)
/*
INSERT INTO appointments (appointment_id, patient_id, doctor_id, scheduled_date, status, reason) VALUES
('APP_001', 'PAT_001', 'DOC_001', '2024-09-15', 'SCHEDULED', 'Consulta de rutina cardiológica'),
('APP_002', 'PAT_002', 'DOC_002', '2024-09-16', 'CONFIRMED', 'Seguimiento neurológico'),
('APP_003', 'PAT_003', 'DOC_003', '2024-09-17', 'SCHEDULED', 'Control pediátrico');
*/
