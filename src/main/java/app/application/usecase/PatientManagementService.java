package app.application.usecase;

import app.application.port.in.PatientManagementUseCase;
import app.domain.model.Patient;
import app.domain.model.PatientId;
import app.domain.model.PersonalInformation;
import app.domain.repository.PatientRepository;
import java.util.List;
import java.util.UUID;

public class PatientManagementService implements PatientManagementUseCase {
    private final PatientRepository patientRepository;
    
    public PatientManagementService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    
    public Patient registerNewPatient(PersonalInformation personalInformation) {
        this.validatePersonalInformation(personalInformation);
        this.validateUniqueDocument(personalInformation.getDocumentNumber());
        
        PatientId newPatientId = this.generateNewPatientId();
        Patient newPatient = new Patient(newPatientId, personalInformation);
        
        return patientRepository.save(newPatient);
    }
    
    public Patient updatePatientInformation(PatientId patientId, PersonalInformation personalInformation) {
        Patient existingPatient = this.findExistingPatient(patientId);
        this.validatePersonalInformation(personalInformation);
        
        Patient updatedPatient = new Patient(patientId, personalInformation);
        return patientRepository.save(updatedPatient);
    }
    
    public Patient findPatientById(PatientId patientId) {
        return this.findExistingPatient(patientId);
    }
    
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    
    public void removePatient(PatientId patientId) {
        this.validatePatientExists(patientId);
        patientRepository.delete(patientId);
    }
    
    public List<Patient> searchPatientsByDocument(String documentNumber) {
        this.validateDocumentNumber(documentNumber);
        return patientRepository.findByDocumentNumber(documentNumber);
    }
    
    private void validatePersonalInformation(PersonalInformation personalInformation) {
        if (personalInformation == null) {
            throw new IllegalArgumentException("Información personal es obligatoria");
        }
    }
    
    private void validateUniqueDocument(String documentNumber) {
        List<Patient> existingPatients = patientRepository.findByDocumentNumber(documentNumber);
        if (!existingPatients.isEmpty()) {
            throw new IllegalArgumentException("Ya existe un paciente con este documento");
        }
    }
    
    private void validateDocumentNumber(String documentNumber) {
        if (documentNumber == null || documentNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Número de documento no puede estar vacío");
        }
    }
    
    private void validatePatientExists(PatientId patientId) {
        if (!patientRepository.exists(patientId)) {
            throw new IllegalArgumentException("Paciente no encontrado");
        }
    }
    
    private Patient findExistingPatient(PatientId patientId) {
        return patientRepository.findById(patientId)
            .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));
    }
    
    private PatientId generateNewPatientId() {
        return new PatientId("PAT_" + UUID.randomUUID().toString());
    }
}
