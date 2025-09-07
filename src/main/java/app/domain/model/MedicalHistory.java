package app.domain.model;

import java.util.List;
import java.util.ArrayList;

public class MedicalHistory {
    private List<String> previousDiagnoses;
    private List<String> currentMedications;
    private List<String> allergies;
    
    public MedicalHistory() {
        this.previousDiagnoses = new ArrayList<>();
        this.currentMedications = new ArrayList<>();
        this.allergies = new ArrayList<>();
    }
    
    public void addDiagnosis(String diagnosis) {
        if (diagnosis != null && !diagnosis.trim().isEmpty()) {
            previousDiagnoses.add(diagnosis);
        }
    }
    
    public void addMedication(String medication) {
        if (medication != null && !medication.trim().isEmpty()) {
            currentMedications.add(medication);
        }
    }
    
    public void addAllergy(String allergy) {
        if (allergy != null && !allergy.trim().isEmpty()) {
            allergies.add(allergy);
        }
    }
    
    // Getters
    public List<String> getPreviousDiagnoses() { return new ArrayList<>(previousDiagnoses); }
    public List<String> getCurrentMedications() { return new ArrayList<>(currentMedications); }
    public List<String> getAllergies() { return new ArrayList<>(allergies); }
}
