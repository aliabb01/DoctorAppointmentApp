package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DoctorService extends AppCompatActivity {


    RecyclerView recycle_serviceDoctor;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_service);
        recycle_serviceDoctor=findViewById(R.id.recycle_serviceDoctor);
     //   doctorServicename=findViewById(R.id.doctor_service_name);
     //   doctorServicedesc=findViewById(R.id.doctor_serviceDescrption);
      //  doctor_addService=findViewById(R.id.addService);
        db = new DBHelper(this);
        Intent intent=getIntent();
      //  String name=intent.getStringExtra("user");
        recycle_serviceDoctor.setLayoutManager(new LinearLayoutManager(this));

        User user= (User) intent.getSerializableExtra("user");
        DBHelper db=new DBHelper(this);
        List<Services> servicesModel=db.getdoctorservice(user.getUser_id());
        if(servicesModel.size()>0) {
            DoctorServicesAdapter servicesAdapter=new DoctorServicesAdapter(servicesModel, DoctorService.this);
            recycle_serviceDoctor.setAdapter(servicesAdapter);

        }
        else {
            Toast.makeText(this, "no item", Toast.LENGTH_SHORT).show();
        }



       /* doctor_addService.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             try {
                 db = new DBHelper(getApplicationContext());

                 String serviceNameLabel = doctorServicename.getText().toString();
                 String descriptionLabel = doctorServicedesc.getText().toString();
                 User user= (User) intent.getSerializableExtra("user");



                     if (db.insertServices(serviceNameLabel,descriptionLabel, user.getUser_id())) {
                         Toast.makeText(DoctorService.this, "done", Toast.LENGTH_SHORT).show();

                     }
                      else{
                             Toast.makeText(DoctorService.this, "can not create", Toast.LENGTH_SHORT).show();
                         }


             } catch (Exception e) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                 builder.setTitle("error");
                 builder.setMessage(e.getMessage());
                 builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         dialogInterface.cancel();
                     }
                 });
             }

         }
     });*/

    }
}