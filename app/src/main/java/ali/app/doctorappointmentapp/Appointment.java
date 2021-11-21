package ali.app.doctorappointmentapp;


import android.widget.CalendarView;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Appointment implements Serializable {
    private int id;
    private Date date;
    private String time;
    private int user_id;
    private  int service_id;


    public Appointment(Date date, String time, int service_id) {
        this.date = date;
        this.time = time;
        this.service_id = service_id;
    }

    public Appointment(Date date, String time) {
        this.date = date;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Appointment() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }
}
