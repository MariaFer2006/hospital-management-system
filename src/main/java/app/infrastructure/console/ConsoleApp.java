package app.infrastructure.console;

import app.application.usecase.AppointmentManagementService;
import app.application.usecase.DoctorManagementService;
import app.application.usecase.PatientManagementService;
import app.domain.model.*;
import app.infrastructure.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsoleApp {

    private final PatientManagementService patientService;
    private final DoctorManagementService doctorService;
    private final AppointmentManagementService appointmentService;
    private final Scanner scanner;

    public ConsoleApp() {
        InMemoryPatientRepository patientRepository = new InMemoryPatientRepository();
        InMemoryDoctorRepository doctorRepository = new InMemoryDoctorRepository();
        InMemoryAppointmentRepository appointmentRepository = new InMemoryAppointmentRepository();

        this.patientService = new PatientManagementService(patientRepository);
        this.doctorService = new DoctorManagementService(doctorRepository);
        this.appointmentService = new AppointmentManagementService(
                appointmentRepository, patientRepository, doctorRepository
        );
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== HOSPITAL MANAGEMENT SYSTEM ===");

        boolean running = true;
        while (running) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestionar Pacientes");
            System.out.println("2. Gestionar Doctores");
            System.out.println("3. Gestionar Citas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    managePatients();
                    break;
                case 2:
                    manageDoctors();
                    break;
                case 3:
                    manageAppointments();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("❌ Opción inválida.");
            }
        }
    }

    private void managePatients() {
        System.out.println("\n--- GESTIÓN DE PACIENTES ---");
        System.out.println("1. Registrar paciente");
        System.out.println("2. Ver pacientes");
        System.out.print("Seleccione una opción: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();
                System.out.print("Documento: ");
                String documento = scanner.nextLine();
                PersonalInformation info = new PersonalInformation(
                        nombre, apellido, documento,
                        LocalDate.of(1990, 1, 1), "555-123", "correo@email.com"
                );
                Patient p = patientService.registerNewPatient(info);
                System.out.println("✅ Paciente registrado: " + p.getPersonalInformation().getFullName());
                break;
            case 2:
                System.out.println("📋 Lista de pacientes:");
                patientService.getAllPatients().forEach(patient ->
                        System.out.println("- " + patient.getPersonalInformation().getFullName())
                );
                break;
            default:
                System.out.println("❌ Opción inválida.");
        }
    }

    private void manageDoctors() {
        System.out.println("\n--- GESTIÓN DE DOCTORES ---");
        System.out.println("1. Registrar doctor");
        System.out.println("2. Ver doctores");
        System.out.print("Seleccione una opción: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();
                System.out.print("Documento: ");
                String documento = scanner.nextLine();
                System.out.print("Especialidad (CARDIOLOGY, PEDIATRICS, DERMATOLOGY): ");
                String esp = scanner.nextLine();
                PersonalInformation info = new PersonalInformation(
                        nombre, apellido, documento,
                        LocalDate.of(1980, 1, 1), "555-999", "doctor@email.com"
                );
                Doctor d = doctorService.registerNewDoctor(info, MedicalSpecialty.valueOf(esp));
                System.out.println("✅ Doctor registrado: " + d.getPersonalInformation().getFullName());
                break;
            case 2:
                System.out.println("📋 Lista de doctores:");
                doctorService.getAllDoctors().forEach(doc ->
                        System.out.println("- " + doc.getPersonalInformation().getFullName() +
                                " | " + doc.getSpecialty().getDisplayName())
                );
                break;
            default:
                System.out.println("❌ Opción inválida.");
        }
    }

    private void manageAppointments() {
        System.out.println("\n--- GESTIÓN DE CITAS ---");
        System.out.println("1. Programar cita");
        System.out.println("2. Ver citas por paciente");
        System.out.print("Seleccione una opción: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.print("ID Paciente: ");
                String pid = scanner.nextLine();
                System.out.print("ID Doctor: ");
                String did = scanner.nextLine();
                LocalDateTime fecha = LocalDateTime.now().plusDays(3);
                AppointmentReason reason = new AppointmentReason("Consulta general");
                Appointment app = appointmentService.scheduleAppointment(
                        new PatientId(pid), new DoctorId(did),
                        new AppointmentDateTime(fecha), reason
                );
                System.out.println("✅ Cita programada: " + app.getAppointmentId().getValue());
                break;
            case 2:
                System.out.print("ID Paciente: ");
                String pId = scanner.nextLine();
                appointmentService.findAppointmentsByPatient(new PatientId(pId))
                        .forEach(a -> System.out.println("- Cita " + a.getAppointmentId().getValue() +
                                " | Fecha: " + a.getScheduledDateTime() +
                                " | Estado: " + a.getStatus().getDisplayName()));
                break;
            default:
                System.out.println("❌ Opción inválida.");
        }
    }

    public static void main(String[] args) {
        new ConsoleApp().start();
    }
}
