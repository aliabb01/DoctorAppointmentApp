package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

public class Doctor extends AppCompatActivity {

    DBHelper db;

    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        /*submitService = findViewById(R.id.submitService);
        serviceName = findViewById(R.id.serviceName);
        description = findViewById(R.id.description);*/

        db = new DBHelper(this);

        userName = findViewById(R.id.doctor_username);

        Intent intent=getIntent();
        User user= (User) intent.getSerializableExtra("user");
        userName.setText(user.getName());

        /*welcome=findViewById(R.id.textView4);
        Intent intent=getIntent();
        Users user= (Users) intent.getSerializableExtra("user");
        welcome.setText("hello"+user.getName());*/

        /*submitService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serviceNameLabel = serviceName.getText().toString();
                String descriptionLabel = description.getText().toString();

                if(serviceNameLabel.equals("") || descriptionLabel.equals("")) {
                    Toast.makeText(Doctor.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    // if values are inserted
                    Boolean insert = db.insertServices(serviceNameLabel, descriptionLabel);
                    if(insert){
                        Toast.makeText(Doctor.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Doctor.this, "Already added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });*/


    }


}