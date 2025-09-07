package app.domain.model;

public class TimeSlot {
    private AppointmentDateTime startTime;
    private AppointmentDateTime endTime;
    
    public TimeSlot(AppointmentDateTime startTime, AppointmentDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public boolean conflictsWith(AppointmentDateTime dateTime) {
        return !dateTime.getValue().isBefore(startTime.getValue()) && 
               !dateTime.getValue().isAfter(endTime.getValue());
    }
    
    // Getters
    public AppointmentDateTime getStartTime() { return startTime; }
    public AppointmentDateTime getEndTime() { return endTime; }
}
