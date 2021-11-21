package ali.app.doctorappointmentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ServicePage extends AppCompatActivity {

    TextView servicepage_patient,service_pageid,text_time;
    Button bookAppointment,pickDate;
    Date date;
    Time Time;
    DBHelper db;
    int h,m,s;
    //RecyclerView recyclerView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_page);
     ///   recyclerView1=findViewById(R.id.service_page);
        servicepage_patient=findViewById(R.id.servicepage_patient);
        pickDate=findViewById(R.id.date);
        service_pageid=findViewById(R.id.service_pageid);
        bookAppointment=findViewById(R.id.book_appointment);
        text_time=findViewById(R.id.text_time);
        /**
         * get user details
         * */
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
      ///  servicepage_patient.setText(String.valueOf(user.getUser_id()));

       Bundle extras = getIntent().getExtras();
        String stringVariableName = extras.getString("service");
        int intVariableName = Integer.parseInt(stringVariableName);

      ///  service_pageid.setText(String.valueOf(intVariableName));
        /**
         * choose the date
         * */
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ServicePage.this);
                builder.setView(R.layout.datepicker);
           AlertDialog alertDialog  =builder.show();
                CalendarView calendarView=alertDialog.findViewById(R.id.calendarView);
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                        try {
                            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                            date=simpleDateFormat.parse(i+"-"+i1+"-"+i2);
                        }catch (Exception e){
                            date=null;
                        }
                    }
                });

            }
        });
         /**
          * choose the time
          * */
        text_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(ServicePage.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                      h=i;
                      m=i1;
                      String time=h+":"+m;
                      SimpleDateFormat f24Hours=new SimpleDateFormat("HH:mm");
                      try{
                          Date date1=f24Hours.parse(time);
                          SimpleDateFormat f12Hours=new SimpleDateFormat("HH:mm aa");
                          text_time.setText(f12Hours.format(date1));
                      ////    Time= (java.sql.Time) f12Hours.parse(i+"-"+i1);

                      }catch (ParseException e){
                  ///        Time=null;
                          e.printStackTrace();
                      }
                    }
                },12,0,false);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(h,m);
                timePickerDialog.show();
                Toast.makeText(getApplicationContext(),"time"+Time,Toast.LENGTH_SHORT).show();
            }
        });

        bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new DBHelper(getApplicationContext());
                Appointment appointment = new Appointment();
                appointment.setDate(date);
                appointment.setTime(text_time.getText().toString());

                db.insertAppointment(appointment,intVariableName,user.getUser_id());
                  Toast.makeText(getApplicationContext(),"register successfully",Toast.LENGTH_SHORT).show();

            }
        });




       /* recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setHasFixedSize(true);
        DBHelper db=new DBHelper(this);
        List<Services> servicesModel=db.getService(intVariableName);
        if(servicesModel.size()>0) {
            ServicePageAdapter servicesAdapter=new ServicePageAdapter(servicesModel, ServicePage.this);
            recyclerView1.setAdapter(servicesAdapter);

        }
        else {
            Toast.makeText(this, "no item", Toast.LENGTH_SHORT).show();
        }*/
    }
}