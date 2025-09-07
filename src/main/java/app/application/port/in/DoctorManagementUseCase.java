package app.application.port.in;

import app.domain.model.Doctor;
import app.domain.model.DoctorId;
import app.domain.model.PersonalInformation;
import app.domain.model.MedicalSpecialty;
import java.util.List;

public interface DoctorManagementUseCase {
    Doctor registerNewDoctor(PersonalInformation personalInformation, MedicalSpecialty specialty);
    Doctor updateDoctorInformation(DoctorId doctorId, PersonalInformation personalInformation);
    Doctor findDoctorById(DoctorId doctorId);
    List<Doctor> getAllDoctors();
    void removeDoctor(DoctorId doctorId);
    List<Doctor> findDoctorsBySpecialty(MedicalSpecialty specialty);
}
