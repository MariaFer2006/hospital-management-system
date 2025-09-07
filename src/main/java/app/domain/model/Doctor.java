package app.domain.model;

import java.util.List;
import java.util.ArrayList;

public class Doctor {
    private DoctorId doctorId;
    private PersonalInformation personalInformation;
    private MedicalSpecialty specialty;
    private List<Appointment> appointments;
    private Schedule workingSchedule;
    
    public Doctor(DoctorId doctorId, PersonalInformation personalInformation, 
                  MedicalSpecialty specialty) {
        this.doctorId = doctorId;
        this.personalInformation = personalInformation;
        this.specialty = specialty;
        this.appointments = new ArrayList<>();
        this.workingSchedule = new Schedule();
        this.validateDoctorCreation();
    }
    
    private void validateDoctorCreation() {
        if (doctorId == null || personalInformation == null || specialty == null) {
            throw new IllegalArgumentException("Todos los campos del doctor son obligatorios");
        }
    }
    
    public boolean isAvailable(AppointmentDateTime dateTime) {
        return workingSchedule.isTimeSlotAvailable(dateTime) && 
               !this.hasAppointmentAt(dateTime);
    }
    
    private boolean hasAppointmentAt(AppointmentDateTime dateTime) {
        return appointments.stream()
            .anyMatch(appointment -> 
                appointment.getScheduledDateTime().equals(dateTime.getValue()));
    }
    
    public void assignAppointment(Appointment appointment) {
        if (this.isAvailable(appointment.getAppointmentDateTime())) {
            appointments.add(appointment);
        }
    }
    
    // Getters
    public DoctorId getDoctorId() { return doctorId; }
    public PersonalInformation getPersonalInformation() { return personalInformation; }
    public MedicalSpecialty getSpecialty() { return specialty; }
    public List<Appointment> getAppointments() { return new ArrayList<>(appointments); }
}
