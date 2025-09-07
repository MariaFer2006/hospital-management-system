package app.application.usecase;

import app.application.port.in.DoctorManagementUseCase;
import app.domain.model.Doctor;
import app.domain.model.DoctorId;
import app.domain.model.PersonalInformation;
import app.domain.model.MedicalSpecialty;
import app.domain.repository.DoctorRepository;
import java.util.List;
import java.util.UUID;

public class DoctorManagementService implements DoctorManagementUseCase {
    private final DoctorRepository doctorRepository;
    
    public DoctorManagementService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    
    public Doctor registerNewDoctor(PersonalInformation personalInformation, MedicalSpecialty specialty) {
        this.validateDoctorRegistration(personalInformation, specialty);
        
        DoctorId newDoctorId = this.generateNewDoctorId();
        Doctor newDoctor = new Doctor(newDoctorId, personalInformation, specialty);
        
        return doctorRepository.save(newDoctor);
    }
    
    public Doctor updateDoctorInformation(DoctorId doctorId, PersonalInformation personalInformation) {
        Doctor existingDoctor = this.findExistingDoctor(doctorId);
        this.validatePersonalInformation(personalInformation);
        
        Doctor updatedDoctor = new Doctor(doctorId, personalInformation, existingDoctor.getSpecialty());
        return doctorRepository.save(updatedDoctor);
    }
    
    public Doctor findDoctorById(DoctorId doctorId) {
        return this.findExistingDoctor(doctorId);
    }
    
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    
    public void removeDoctor(DoctorId doctorId) {
        this.validateDoctorExists(doctorId);
        doctorRepository.delete(doctorId);
    }
    
    public List<Doctor> findDoctorsBySpecialty(MedicalSpecialty specialty) {
        this.validateSpecialty(specialty);
        return doctorRepository.findBySpecialty(specialty);
    }
    
    private void validateDoctorRegistration(PersonalInformation personalInformation, MedicalSpecialty specialty) {
        this.validatePersonalInformation(personalInformation);
        this.validateSpecialty(specialty);
    }
    
    private void validatePersonalInformation(PersonalInformation personalInformation) {
        if (personalInformation == null) {
            throw new IllegalArgumentException("Información personal es obligatoria");
        }
    }
    
    private void validateSpecialty(MedicalSpecialty specialty) {
        if (specialty == null) {
            throw new IllegalArgumentException("Especialidad médica es obligatoria");
        }
    }
    
    private void validateDoctorExists(DoctorId doctorId) {
        if (!doctorRepository.exists(doctorId)) {
            throw new IllegalArgumentException("Doctor no encontrado");
        }
    }
    
    private Doctor findExistingDoctor(DoctorId doctorId) {
        return doctorRepository.findById(doctorId)
            .orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado"));
    }
    
    private DoctorId generateNewDoctorId() {
        return new DoctorId("DOC_" + UUID.randomUUID().toString());
    }
}
