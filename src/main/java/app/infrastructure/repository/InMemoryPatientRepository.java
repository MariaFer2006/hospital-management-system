package app.infrastructure.repository;

import app.domain.model.Patient;
import app.domain.model.PatientId;
import app.domain.repository.PatientRepository;

import java.util.*;

public class InMemoryPatientRepository implements PatientRepository {
    private final Map<String, Patient> patients = new HashMap<>();

    @Override
    public Patient save(Patient patient) {
        patients.put(patient.getPatientId().getValue(), patient);
        return patient;
    }

    @Override
    public Optional<Patient> findById(PatientId patientId) {
        return Optional.ofNullable(patients.get(patientId.getValue()));
    }

    @Override
    public List<Patient> findAll() {
        return new ArrayList<>(patients.values());
    }

    @Override
    public void delete(PatientId patientId) {
        patients.remove(patientId.getValue());
    }

    @Override
    public boolean exists(PatientId patientId) {
        return patients.containsKey(patientId.getValue());
    }

    @Override
    public List<Patient> findByDocumentNumber(String documentNumber) {
        return patients.values().stream()
                .filter(p -> p.getPersonalInformation().getDocumentNumber().equals(documentNumber))
                .toList();
    }
}
