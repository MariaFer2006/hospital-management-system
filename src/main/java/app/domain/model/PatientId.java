package app.domain.model;

public class PatientId {
    private final String value;
    
    public PatientId(String value) {
        this.validateId(value);
        this.value = value;
    }
    
    private void validateId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("ID del paciente no puede estar vacío");
        }
    }
    
    public String getValue() { return value; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PatientId patientId = (PatientId) obj;
        return value.equals(patientId.value);
    }
    
    @Override
    public int hashCode() { return value.hashCode(); }
}
