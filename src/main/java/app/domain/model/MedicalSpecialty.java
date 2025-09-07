package app.domain.model;

public enum MedicalSpecialty {
    CARDIOLOGY("Cardiología"),
    DERMATOLOGY("Dermatología"),
    NEUROLOGY("Neurología"),
    PEDIATRICS("Pediatría"),
    PSYCHIATRY("Psiquiatría"),
    GENERAL_MEDICINE("Medicina General");
    
    private final String displayName;
    
    MedicalSpecialty(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() { return displayName; }
}
