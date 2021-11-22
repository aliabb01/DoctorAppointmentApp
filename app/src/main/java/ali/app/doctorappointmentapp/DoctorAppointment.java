package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DoctorAppointment extends AppCompatActivity {

    RecyclerView recycle_doctor_appointment;
    TextView my_service;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);
        recycle_doctor_appointment=findViewById(R.id.recycle_doctor_appointment);
        my_service=findViewById(R.id.my_service);
        db = new DBHelper(this);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        int value=db.getdoctorname(user.getUser_id());
        my_service.setText(String.valueOf(value));

        recycle_doctor_appointment.setLayoutManager(new LinearLayoutManager(this));
        recycle_doctor_appointment.setHasFixedSize(true);

        List<Appointment> servicesModel=db.getappointments(value);
        if(servicesModel.size()>0) {
            DoctorAppointmentAdapter historyRecycle=new DoctorAppointmentAdapter(servicesModel, DoctorAppointment.this);
            recycle_doctor_appointment.setAdapter(historyRecycle);

        }
        else {
            Toast.makeText(this, "no item", Toast.LENGTH_SHORT).show();
        }

    }

}