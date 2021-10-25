package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class doctor_service extends AppCompatActivity {

    EditText doctor_serviceName,doctor_serviceDesc,doctor_id;
    TextView we;
    Button doctor_addService;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_service);

        doctor_serviceName=findViewById(R.id.doctor_service_name);
        doctor_serviceDesc=findViewById(R.id.doctor_serviceDescrption);
        doctor_id=findViewById(R.id.doctor_id);
        doctor_addService=findViewById(R.id.addService);
        we=findViewById(R.id.we);
        db = new DBHelper(this);
        Intent intent=getIntent();
      //  String name=intent.getStringExtra("user");

        Users user= (Users) intent.getSerializableExtra("user");

         we.setText("hello" + user.getUser_id());

        doctor_addService.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
         //    String serviceNameLabel = doctor_serviceName.getText().toString();
          //   String descriptionLabel = doctor_serviceDesc.getText().toString();
         //     int id=we.setText(user.getId()).toString();
             /// int id=user.getId();
             try {
                 db = new DBHelper(getApplicationContext());
               //  Services service = new Services();
                 //service.setName(doctor_serviceName.getText().toString());
               ///  service.setDescription(doctor_serviceDesc.getText().toString());
                /// service.setUser_id(user.getUser_id());
                // service.setUser_id(we.getText().toString());
                 String serviceNameLabel = doctor_serviceName.getText().toString();
                 String descriptionLabel = doctor_serviceDesc.getText().toString();
                 Users user= (Users) intent.getSerializableExtra("user");



                     if (db.insertServices(serviceNameLabel,descriptionLabel, user.getUser_id())) {
                         Toast.makeText(doctor_service.this, "done", Toast.LENGTH_SHORT).show();

                     }
                      else{
                             Toast.makeText(doctor_service.this, "can not create", Toast.LENGTH_SHORT).show();
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
     });

    }
}