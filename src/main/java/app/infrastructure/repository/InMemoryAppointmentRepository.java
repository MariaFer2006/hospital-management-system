package app.infrastructure.repository;

import app.domain.model.Appointment;
import app.domain.model.AppointmentId;
import app.domain.model.PatientId;
import app.domain.model.DoctorId;
import app.domain.repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.*;

public class InMemoryAppointmentRepository implements AppointmentRepository {
    private final Map<String, Appointment> appointments = new HashMap<>();

    @Override
    public Appointment save(Appointment appointment) {
        appointments.put(appointment.getAppointmentId().getValue(), appointment);
        return appointment;
    }

    @Override
    public Optional<Appointment> findById(AppointmentId id) {
        return Optional.ofNullable(appointments.get(id.getValue()));
    }

    @Override
    public List<Appointment> findAll() {
        return new ArrayList<>(appointments.values());
    }

    @Override
    public void delete(AppointmentId id) {
        appointments.remove(id.getValue());
    }

    @Override
    public List<Appointment> findByPatientId(PatientId patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments.values()) {
            if (a.getPatientId().equals(patientId)) {
                result.add(a);
            }
        }
        return result;
    }

    @Override
    public List<Appointment> findByDoctorId(DoctorId doctorId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments.values()) {
            if (a.getDoctorId().equals(doctorId)) {
                result.add(a);
            }
        }
        return result;
    }

    @Override
    public List<Appointment> findByDate(LocalDate date) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments.values()) {
            // ✅ Aquí corregido: getScheduledDateTime() devuelve LocalDateTime,
            // usamos .toLocalDate() en lugar de .getValue()
            if (a.getScheduledDateTime().toLocalDate().equals(date)) {
                result.add(a);
            }
        }
        return result;
    }
}
