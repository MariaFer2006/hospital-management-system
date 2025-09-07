package app.domain.model;

public class AppointmentId {
    private final String value;
    
    public AppointmentId(String value) {
        this.validateId(value);
        this.value = value;
    }
    
    private void validateId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("ID de la cita no puede estar vacío");
        }
    }
    
    public String getValue() { return value; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AppointmentId appointmentId = (AppointmentId) obj;
        return value.equals(appointmentId.value);
    }
    
    @Override
    public int hashCode() { return value.hashCode(); }
}
