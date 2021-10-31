package ali.app.doctorappointmentapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;

public class Profile extends AppCompatActivity {

    DBHelper db;

    private TextView name;
    private TextView email;
    private TextView role;

    private ImageButton goBackBtn;
    private ImageView imageView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        imageView3=findViewById(R.id.profileImageChild);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url="";
        ImageRequest imageRequest=new ImageRequest("https://picsum.photos/300", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView3.setImageBitmap(response);
            }
        }, 300, 300, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Profile.this,"something wrong",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

/*        imageView3.setShapeAppearanceModel(imageView3.getShapeAppearanceModel()
        .toBuilder().setAllCorners(CornerFamily.ROUNDED, 300).build());*/

        MySingleton.getInstance(Profile.this).addRequest(imageRequest);



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
