package app.domain.model;

import java.time.LocalDateTime;

public class Appointment {
    private AppointmentId appointmentId;
    private PatientId patientId;
    private DoctorId doctorId;
    private AppointmentDateTime scheduledDateTime;
    private AppointmentStatus status;
    private AppointmentReason reason;
    
    public Appointment(AppointmentId appointmentId, PatientId patientId, 
                      DoctorId doctorId, AppointmentDateTime scheduledDateTime,
                      AppointmentReason reason) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.scheduledDateTime = scheduledDateTime;
        this.reason = reason;
        this.status = AppointmentStatus.SCHEDULED;
        this.validateAppointmentCreation();
    }
    
    private void validateAppointmentCreation() {
        if (appointmentId == null || patientId == null || 
            doctorId == null || scheduledDateTime == null) {
            throw new IllegalArgumentException("Todos los campos de la cita son obligatorios");
        }
    }
    
    public void confirmAppointment() {
        if (status == AppointmentStatus.SCHEDULED) {
            status = AppointmentStatus.CONFIRMED;
        }
    }
    
    public void cancelAppointment() {
        if (status != AppointmentStatus.COMPLETED) {
            status = AppointmentStatus.CANCELLED;
        }
    }
    
    public void completeAppointment() {
        if (status == AppointmentStatus.CONFIRMED) {
            status = AppointmentStatus.COMPLETED;
        }
    }
    
    // Getters
    public AppointmentId getAppointmentId() { return appointmentId; }
    public PatientId getPatientId() { return patientId; }
    public DoctorId getDoctorId() { return doctorId; }
    public LocalDateTime getScheduledDateTime() { return scheduledDateTime.getValue(); }
    public AppointmentDateTime getAppointmentDateTime() { return scheduledDateTime; }
    public AppointmentStatus getStatus() { return status; }
    public AppointmentReason getReason() { return reason; }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", scheduledDateTime=" + scheduledDateTime.getValue() +
                ", status=" + status +
                ", reason=" + reason +
                '}';
    }
}
