package app.domain.repository;

import app.domain.model.Patient;
import app.domain.model.PatientId;
import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    Patient save(Patient patient);
    Optional<Patient> findById(PatientId patientId);
    List<Patient> findAll();
    void delete(PatientId patientId);
    boolean exists(PatientId patientId);
    List<Patient> findByDocumentNumber(String documentNumber);
}
