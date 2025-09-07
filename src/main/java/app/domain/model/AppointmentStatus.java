package app.domain.model;

public enum AppointmentStatus {
    SCHEDULED("Programada"),
    CONFIRMED("Confirmada"),
    CANCELLED("Cancelada"),
    COMPLETED("Completada");

    private final String displayName;

    AppointmentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
