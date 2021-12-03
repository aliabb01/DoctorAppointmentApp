package ali.app.doctorappointmentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ServicePage extends AppCompatActivity {

    TextView text_time_label, day, month, year;
    Button bookAppointment;
    ImageButton pickDate, text_time;
    ConstraintLayout appointment_layout;
    Date date;
    Time Time;
    DBHelper db;
    int h,m,s;

    boolean formValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_page);
        pickDate=findViewById(R.id.date);
        bookAppointment=findViewById(R.id.book_appointment);
        text_time=findViewById(R.id.text_time);
        text_time_label=findViewById(R.id.text_time_label);
        appointment_layout=findViewById(R.id.appointment_layout);

        day = findViewById(R.id.appointmentReg_day);
        month = findViewById(R.id.appointmentReg_month);
        year = findViewById(R.id.appointmentReg_year);


        /**
         * get user details
         * */
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        Bundle extras = getIntent().getExtras();
        String stringVariableName = extras.getString("service");
        int intVariableName = Integer.parseInt(stringVariableName);

        /**
         * choose the date
         * */
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ServicePage.this);
                builder.setView(R.layout.datepicker);
                AlertDialog alertDialog=builder.show();
                CalendarView calendarView=alertDialog.findViewById(R.id.calendarView);
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                        try {
                            i1 = (i1+1) % 12;
                            if(i1==0) {
                                i1=12;
                            }
                            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                            date=simpleDateFormat.parse(i+"-"+i1+"-"+i2);

                            day.setText(String.valueOf(i2));
                            month.setText(String.valueOf(i1));
                            year.setText(String.valueOf(i));
                            Log.d("SOMETAG", "onSelectedDayChange: "+i2+ " "+i1);

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
                          SimpleDateFormat f24=new SimpleDateFormat("HH:mm");
                          text_time_label.setText(f24.format(date1));

                      }catch (ParseException e){
                          e.printStackTrace();
                      }
                    }
                },24,0,true);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(h,m);
                timePickerDialog.show();
            }
        });

        // Notification Channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Appointment Notification", "Appointment Notification name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new DBHelper(getApplicationContext());
                Appointment appointment = new Appointment();
                appointment.setDate(date);
                appointment.setTime(text_time_label.getText().toString());

                boolean insertAp = db.insertAppointment(appointment, intVariableName, user.getUser_id());
                // Toast.makeText(getApplicationContext(),"register successfully",Toast.LENGTH_SHORT).show();

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

                if(text_time_label.getText()!="") {
                    Snackbar.make(appointment_layout, "appointment created successfully", Snackbar.LENGTH_SHORT).show();

                    // Notification builder here
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(ServicePage.this, "Appointment Notification");
                    builder.setSmallIcon(R.drawable.ic_baseline_notifications_24);
                    builder.setContentTitle("New Appointment");
                    builder.setContentText("You have registered an appointment");
                    builder.setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("You have registered an appointment for " + simpleDateFormat.format(appointment.getDate()) + " / " + appointment.getTime()));
                    builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                    // Call the notification using builder instance above
                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(ServicePage.this);
                    managerCompat.notify(user.getUser_id(), builder.build());
                }
                else {
                    Toast.makeText(ServicePage.this, "Failed to insert appointment", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}