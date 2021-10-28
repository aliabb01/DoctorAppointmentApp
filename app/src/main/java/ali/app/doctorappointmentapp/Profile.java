package ali.app.doctorappointmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    DBHelper db;

    private TextView name;
    private TextView email;
    private TextView role;

    private ImageButton goBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Intent intent = getIntent();

        db = new DBHelper(this);

        User user = (User) intent.getSerializableExtra("user");

        Log.d("Tag", "onCreate: " + user);

        name = findViewById(R.id.profile_nameLabel);
        email = findViewById(R.id.profile_about_email);
        role = findViewById(R.id.profile_about_role);
        goBackBtn = findViewById(R.id.profile_goBack);

        name.setText(user.getName());
        email.setText(user.getEmail());
        role.setText(user.getRole());

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

}
