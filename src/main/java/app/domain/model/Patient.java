package app.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Patient {
    private PatientId patientId;
    private PersonalInformation personalInformation;
    private MedicalHistory medicalHistory;
    private List<Appointment> appointments;
    
    public Patient(PatientId patientId, PersonalInformation personalInformation) {
        this.patientId = patientId;
        this.personalInformation = personalInformation;
        this.medicalHistory = new MedicalHistory();
        this.appointments = new ArrayList<>();
        this.validatePatientCreation();
    }
    
    private void validatePatientCreation() {
        if (patientId == null || personalInformation == null) {
            throw new IllegalArgumentException("ID del paciente e información personal son obligatorios");
        }
    }
    
    public void addAppointment(Appointment appointment) {
        if (this.canScheduleAppointment(appointment)) {
            appointments.add(appointment);
        }
    }
    
    private boolean canScheduleAppointment(Appointment appointment) {
        return !this.hasConflictingAppointment(appointment.getScheduledDateTime());
    }
    
    private boolean hasConflictingAppointment(LocalDateTime scheduledDateTime) {
        return appointments.stream()
            .anyMatch(appointment -> 
                appointment.getScheduledDateTime().equals(scheduledDateTime));
    }
    
    // Getters
    public PatientId getPatientId() { return patientId; }
    public PersonalInformation getPersonalInformation() { return personalInformation; }
    public MedicalHistory getMedicalHistory() { return medicalHistory; }
    public List<Appointment> getAppointments() { return new ArrayList<>(appointments); }
}
