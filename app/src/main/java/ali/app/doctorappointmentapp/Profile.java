package ali.app.doctorappointmentapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.snackbar.Snackbar;

public class Profile extends AppCompatActivity {

    DBHelper db;

    private TextView name;
    private TextView email;
    private TextView role;
    private Button deleteAccount,updateAccount;
    AlertDialog.Builder builder;
    private ImageButton goBackBtn;
    private Button history;
    private ImageView imageView3;
    ConstraintLayout profile_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        imageView3=findViewById(R.id.profileImageChild);
        history=findViewById(R.id.history);
        deleteAccount=findViewById(R.id.usrDeleteAccount);
        updateAccount=findViewById(R.id.usrUpdateAccount);
        profile_layout=findViewById(R.id.profile_layout);
        builder = new AlertDialog.Builder(this);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url="";

        Intent intent = getIntent();
        db = new DBHelper(this);
        User user = (User) intent.getSerializableExtra("user");
        Log.d("Tag", "onCreate: " + user);

        ImageRequest imageRequest=new ImageRequest("https://picsum.photos/id/" + String.valueOf(user.getUser_id()) + "/300/300", new Response.Listener<Bitmap>() {
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
        /**
         *
         * see the history of user
         * */
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, MyHistory.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        /**update user account
         * */
        updateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, UpdateAccount.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uncomment the below code to Set the message and title from the strings.xml file
              //  builder.setMessage("DO you want to delete your account?") .setTitle("Delete account");

                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to delete your account?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                db.deleteUser(user.getUser_id());
                                Intent intent=new Intent(Profile.this, MainActivity.class);
                                startActivity(intent);
                                Snackbar.make(profile_layout,"your accoutn have been delete it",Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();

                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Delete account");
                alert.show();
            }

        });

    }

}
