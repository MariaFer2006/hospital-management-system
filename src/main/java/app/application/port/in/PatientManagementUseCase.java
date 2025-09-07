package app.application.port.in;

import app.domain.model.Patient;
import app.domain.model.PatientId;
import app.domain.model.PersonalInformation;
import java.util.List;

public interface PatientManagementUseCase {
    Patient registerNewPatient(PersonalInformation personalInformation);
    Patient updatePatientInformation(PatientId patientId, PersonalInformation personalInformation);
    Patient findPatientById(PatientId patientId);
    List<Patient> getAllPatients();
    void removePatient(PatientId patientId);
    List<Patient> searchPatientsByDocument(String documentNumber);
}
