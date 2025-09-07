package app.application.usecase;

import app.application.port.in.AppointmentManagementUseCase;
import app.domain.model.Appointment;
import app.domain.model.AppointmentId;
import app.domain.model.PatientId;
import app.domain.model.DoctorId;
import app.domain.model.AppointmentDateTime;
import app.domain.model.AppointmentReason;
import app.domain.model.Patient;
import app.domain.model.Doctor;
import app.domain.repository.AppointmentRepository;
import app.domain.repository.PatientRepository;
import app.domain.repository.DoctorRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AppointmentManagementService implements AppointmentManagementUseCase {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    
    public AppointmentManagementService(AppointmentRepository appointmentRepository,
                                       PatientRepository patientRepository,
                                       DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }
    
    public Appointment scheduleAppointment(PatientId patientId, DoctorId doctorId,
                                          AppointmentDateTime dateTime, AppointmentReason reason) {
        this.validateAppointmentScheduling(patientId, doctorId, dateTime, reason);
        this.validateDoctorAvailability(doctorId, dateTime);
        
        AppointmentId newAppointmentId = this.generateNewAppointmentId();
        Appointment newAppointment = new Appointment(newAppointmentId, patientId, doctorId, dateTime, reason);
        
        return appointmentRepository.save(newAppointment);
    }
    
    public Appointment confirmAppointment(AppointmentId appointmentId) {
        Appointment appointment = this.findExistingAppointment(appointmentId);
        appointment.confirmAppointment();
        
        return appointmentRepository.save(appointment);
    }
    
    public Appointment cancelAppointment(AppointmentId appointmentId) {
        Appointment appointment = this.findExistingAppointment(appointmentId);
        appointment.cancelAppointment();
        
        return appointmentRepository.save(appointment);
    }
    
    public Appointment completeAppointment(AppointmentId appointmentId) {
        Appointment appointment = this.findExistingAppointment(appointmentId);
        appointment.completeAppointment();
        
        return appointmentRepository.save(appointment);
    }
    
    public List<Appointment> findAppointmentsByPatient(PatientId patientId) {
        this.validatePatientExists(patientId);
        return appointmentRepository.findByPatientId(patientId);
    }
    
    public List<Appointment> findAppointmentsByDoctor(DoctorId doctorId) {
        this.validateDoctorExists(doctorId);
        return appointmentRepository.findByDoctorId(doctorId);
    }
    
    public List<Appointment> findAppointmentsByDate(LocalDate date) {
        this.validateDate(date);
        return appointmentRepository.findByDate(date);
    }
    
    private void validateAppointmentScheduling(PatientId patientId, DoctorId doctorId,
                                              AppointmentDateTime dateTime, AppointmentReason reason) {
        this.validatePatientExists(patientId);
        this.validateDoctorExists(doctorId);
        this.validateDateTime(dateTime);
        this.validateReason(reason);
    }
    
    private void validateDoctorAvailability(DoctorId doctorId, AppointmentDateTime dateTime) {
        Doctor doctor = this.findExistingDoctor(doctorId);
        if (!doctor.isAvailable(dateTime)) {
            throw new IllegalArgumentException("Doctor no disponible en la fecha solicitada");
        }
    }
    
    private void validatePatientExists(PatientId patientId) {
        if (!patientRepository.exists(patientId)) {
            throw new IllegalArgumentException("Paciente no encontrado");
        }
    }
    
    private void validateDoctorExists(DoctorId doctorId) {
        if (!doctorRepository.exists(doctorId)) {
            throw new IllegalArgumentException("Doctor no encontrado");
        }
    }
    
    private void validateDateTime(AppointmentDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("Fecha y hora de la cita son obligatorias");
        }
    }
    
    private void validateReason(AppointmentReason reason) {
        if (reason == null) {
            throw new IllegalArgumentException("Motivo de la cita es obligatorio");
        }
    }
    
    private void validateDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Fecha no puede ser nula");
        }
    }
    
    private Appointment findExistingAppointment(AppointmentId appointmentId) {
        return appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));
    }
    
    private Doctor findExistingDoctor(DoctorId doctorId) {
        return doctorRepository.findById(doctorId)
            .orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado"));
    }
    
    private AppointmentId generateNewAppointmentId() {
        return new AppointmentId("APP_" + UUID.randomUUID().toString());
    }
}
