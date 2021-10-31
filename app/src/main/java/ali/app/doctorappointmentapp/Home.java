package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.android.material.imageview.ShapeableImageView;

public class Home extends AppCompatActivity {
    TextView welcome, welcome1, welcome2;
    //ImageButton patient_profileIcon;
    private ShapeableImageView patient_profileIcon;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        welcome = (TextView) findViewById(R.id.welcome_user);
        patient_profileIcon=findViewById(R.id.patient_profileIcon);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        welcome.setText(user.getName());
        String url="";
        ImageRequest imageRequest=new ImageRequest("https://picsum.photos/200/300", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                patient_profileIcon.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this,"something wrong",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(Home.this).addRequest(imageRequest);

        patient_profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Profile.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });


    }
}