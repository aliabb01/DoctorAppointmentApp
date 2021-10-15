package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin extends AppCompatActivity {
     Button services,userList,doctor_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
       services=findViewById(R.id.services);
       userList=findViewById(R.id.users);
       doctor_list=findViewById(R.id.doctorList);

       userList.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(Admin.this, UserList.class);
               startActivity(intent);
           }
       });

        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin.this, ServicesList.class);
                startActivity(intent);
            }
        });


    }
}