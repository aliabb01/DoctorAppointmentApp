package ali.app.doctorappointmentapp;

public class Treatment {
    private int id;
    private String description;
    private int appointmentId;

    public Treatment(String description, int appointmentId) {
        this.description = description;
        this.appointmentId = appointmentId;
    }

    public Treatment(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
}
