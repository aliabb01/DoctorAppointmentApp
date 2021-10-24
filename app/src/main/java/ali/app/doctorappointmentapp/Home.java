package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    TextView welcome, welcome1, welcome2;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        welcome = (TextView) findViewById(R.id.welcome_user);
        welcome1 = (TextView) findViewById(R.id.homeEmail);
        welcome2 = (TextView) findViewById(R.id.homePassword);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        /// welcome.setText("Hello"+user);
        welcome.setText("hello " + user.getName());
        welcome1.setText("hello " + user.getEmail());
        welcome2.setText("hello " + user.getPassword());
      /*  if(user != null) {
            String userInfo = db.seedetails(user);
            Log.d("myTag", "onClick: ID: " + userInfo);
        }
        else
        {

        }*/
    }
}