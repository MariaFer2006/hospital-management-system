package app.domain.model;

public class AppointmentReason {
    private final String value;
    
    public AppointmentReason(String value) {
        this.validateReason(value);
        this.value = value;
    }
    
    private void validateReason(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Motivo de la cita no puede estar vacío");
        }
    }
    
    public String getValue() { return value; }
}
