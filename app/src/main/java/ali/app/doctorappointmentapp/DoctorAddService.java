package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorAddService extends AppCompatActivity {
    EditText doctorServicename,doctorServicedesc;
    DBHelper db;
    Button doctor_addService;
    int i=7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_add_service);

           doctorServicename=findViewById(R.id.doctor_service_name);
           doctorServicedesc=findViewById(R.id.doctor_serviceDescrption);
           doctor_addService=findViewById(R.id.addService);
        db = new DBHelper(this);
        Intent intent=getIntent();
        User user= (User) intent.getSerializableExtra("user");
        doctor_addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    db = new DBHelper(getApplicationContext());

                    String serviceNameLabel = doctorServicename.getText().toString();
                    String descriptionLabel = doctorServicedesc.getText().toString();
                    User user= (User) intent.getSerializableExtra("user");

                    if(serviceNameLabel.length()<=0 || descriptionLabel.length()<=0){
                        Toast.makeText(DoctorAddService.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                    }else {
                        Services services = new Services(serviceNameLabel, descriptionLabel);


                        db.insertServices(services,user.getUser_id());
                            Toast.makeText(DoctorAddService.this, "done", Toast.LENGTH_SHORT).show();

                                finish();
                    }



            }
        });
    }
}