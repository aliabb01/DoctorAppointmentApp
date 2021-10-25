package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class Doctor extends AppCompatActivity {

    DBHelper db;


    TextView welcome;
    private MaterialCardView card_view;

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


        MaterialCardView card_view = (MaterialCardView) findViewById(R.id.profile_card_1); // creating a CardView and assigning a value.

        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctor.this, DoctorService.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });


    }


}