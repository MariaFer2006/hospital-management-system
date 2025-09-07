package app.domain.model;

import java.time.LocalDate;

public class PersonalInformation {
    private final String firstName;
    private final String lastName;
    private final String documentNumber;
    private final LocalDate birthDate;
    private final String phoneNumber;
    private final String email;
    
    public PersonalInformation(String firstName, String lastName, String documentNumber,
                              LocalDate birthDate, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.validatePersonalInformation();
    }
    
    private void validatePersonalInformation() {
        if (firstName == null || lastName == null || documentNumber == null) {
            throw new IllegalArgumentException("Nombre, apellido y documento son obligatorios");
        }
    }
    
    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDocumentNumber() { return documentNumber; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getFullName() { return firstName + " " + lastName; }
}
