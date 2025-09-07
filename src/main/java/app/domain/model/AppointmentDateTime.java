package app.domain.model;

import java.time.LocalDateTime;

public class AppointmentDateTime {
    private final LocalDateTime value;
    
    public AppointmentDateTime(LocalDateTime value) {
        this.validateDateTime(value);
        this.value = value;
    }
    
    private void validateDateTime(LocalDateTime value) {
        if (value == null) {
            throw new IllegalArgumentException("Fecha y hora de la cita no pueden ser nulas");
        }
        if (value.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La cita no puede programarse en el pasado");
        }
    }
    
    public LocalDateTime getValue() { return value; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AppointmentDateTime that = (AppointmentDateTime) obj;
        return value.equals(that.value);
    }
    
    @Override
    public int hashCode() { return value.hashCode(); }

    @Override
    public String toString() {
        return value.toString();
    }
}
