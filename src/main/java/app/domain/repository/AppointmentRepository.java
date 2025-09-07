package app.domain.repository;

import app.domain.model.Appointment;
import app.domain.model.AppointmentId;
import app.domain.model.PatientId;
import app.domain.model.DoctorId;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    Optional<Appointment> findById(AppointmentId appointmentId);
    List<Appointment> findAll();
    void delete(AppointmentId appointmentId);
    List<Appointment> findByPatientId(PatientId patientId);
    List<Appointment> findByDoctorId(DoctorId doctorId);
    List<Appointment> findByDate(LocalDate date);
}
