package app.domain.repository;

import app.domain.model.Doctor;
import app.domain.model.DoctorId;
import app.domain.model.MedicalSpecialty;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository {
    Doctor save(Doctor doctor);
    Optional<Doctor> findById(DoctorId doctorId);
    List<Doctor> findAll();
    void delete(DoctorId doctorId);
    boolean exists(DoctorId doctorId);
    List<Doctor> findBySpecialty(MedicalSpecialty specialty);
}
