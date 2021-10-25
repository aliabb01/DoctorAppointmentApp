package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

public class Doctor extends AppCompatActivity {

    DBHelper db;


    TextView welcome;
    private CardView card_view;

    private TextView userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Intent intent = getIntent();
        //  String name=intent.getStringExtra("user");

        User user = (User) intent.getSerializableExtra("user");


        userName = findViewById(R.id.doctor_username);


        userName.setText(user.getName());

        /*welcome=findViewById(R.id.textView4);
        Intent intent=getIntent();
        Users user= (Users) intent.getSerializableExtra("user");
        welcome.setText("hello"+user.getName());*/


        CardView card_view = (CardView) findViewById(R.id.service_doctor); // creating a CardView and assigning a value.

        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctor.this, doctor_service.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });


    }


}