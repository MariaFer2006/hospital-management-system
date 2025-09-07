package app.infrastructure.repository;

import app.domain.model.Doctor;
import app.domain.model.DoctorId;
import app.domain.model.MedicalSpecialty;
import app.domain.repository.DoctorRepository;

import java.util.*;

public class InMemoryDoctorRepository implements DoctorRepository {
    private final Map<String, Doctor> doctors = new HashMap<>();

    @Override
    public Doctor save(Doctor doctor) {
        doctors.put(doctor.getDoctorId().getValue(), doctor);
        return doctor;
    }

    @Override
    public Optional<Doctor> findById(DoctorId doctorId) {
        return Optional.ofNullable(doctors.get(doctorId.getValue()));
    }

    @Override
    public List<Doctor> findAll() {
        return new ArrayList<>(doctors.values());
    }

    @Override
    public void delete(DoctorId doctorId) {
        doctors.remove(doctorId.getValue());
    }

    @Override
    public boolean exists(DoctorId doctorId) {
        return doctors.containsKey(doctorId.getValue());
    }

    @Override
    public List<Doctor> findBySpecialty(MedicalSpecialty specialty) {
        return doctors.values().stream()
                .filter(d -> d.getSpecialty().equals(specialty))
                .toList();
    }
}
