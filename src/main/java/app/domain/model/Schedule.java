package app.domain.model;

import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class Schedule {
    private LocalTime startTime;
    private LocalTime endTime;
    private List<TimeSlot> unavailableSlots;
    
    public Schedule() {
        this.startTime = LocalTime.of(8, 0);
        this.endTime = LocalTime.of(17, 0);
        this.unavailableSlots = new ArrayList<>();
    }
    
    public boolean isTimeSlotAvailable(AppointmentDateTime dateTime) {
        return this.isWithinWorkingHours() && !this.isSlotUnavailable(dateTime);
    }
    
    private boolean isWithinWorkingHours() {
        LocalTime appointmentTime = LocalTime.now();
        return appointmentTime.isAfter(startTime) && appointmentTime.isBefore(endTime);
    }
    
    private boolean isSlotUnavailable(AppointmentDateTime dateTime) {
        return unavailableSlots.stream()
            .anyMatch(slot -> slot.conflictsWith(dateTime));
    }
    
    // Getters
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
}
