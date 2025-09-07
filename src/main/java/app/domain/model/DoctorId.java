package app.domain.model;

public class DoctorId {
    private final String value;
    
    public DoctorId(String value) {
        this.validateId(value);
        this.value = value;
    }
    
    private void validateId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("ID del doctor no puede estar vacío");
        }
    }
    
    public String getValue() { return value; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DoctorId doctorId = (DoctorId) obj;
        return value.equals(doctorId.value);
    }
    
    @Override
    public int hashCode() { return value.hashCode(); }
}
