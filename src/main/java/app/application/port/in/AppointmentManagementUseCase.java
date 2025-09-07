package app.application.port.in;

import app.domain.model.Appointment;
import app.domain.model.AppointmentId;
import app.domain.model.PatientId;
import app.domain.model.DoctorId;
import app.domain.model.AppointmentDateTime;
import app.domain.model.AppointmentReason;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentManagementUseCase {
    Appointment scheduleAppointment(PatientId patientId, DoctorId doctorId, 
                                   AppointmentDateTime dateTime, AppointmentReason reason);
    Appointment confirmAppointment(AppointmentId appointmentId);
    Appointment cancelAppointment(AppointmentId appointmentId);
    Appointment completeAppointment(AppointmentId appointmentId);
    List<Appointment> findAppointmentsByPatient(PatientId patientId);
    List<Appointment> findAppointmentsByDoctor(DoctorId doctorId);
    List<Appointment> findAppointmentsByDate(LocalDate date);
}
