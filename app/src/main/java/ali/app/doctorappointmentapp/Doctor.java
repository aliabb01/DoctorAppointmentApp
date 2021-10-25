package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Doctor extends AppCompatActivity {

    DBHelper db;

    private Button submitService;
    private EditText serviceName;
    private EditText description;
    TextView welcome;
       private CardView card_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Intent intent=getIntent();
        //  String name=intent.getStringExtra("user");

        Users user= (Users) intent.getSerializableExtra("user");




        CardView card_view = (CardView) findViewById(R.id.service_doctor); // creating a CardView and assigning a value.

        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Doctor.this, doctor_service.class);
                intent.putExtra("user", user);
                startActivity(intent);
           }
        });






    }


}