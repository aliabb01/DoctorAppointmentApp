package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class Doctor extends AppCompatActivity {

    DBHelper db;


    TextView welcome;
    private MaterialCardView card_view;
    private FloatingActionButton  floatingActionButton;
    private TextView userName;
    private ImageView profileIcon;
    Boolean isopen=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Intent intent = getIntent();
        //  String name=intent.getStringExtra("user");

        db = new DBHelper(this);

        User user = (User) intent.getSerializableExtra("user");


        userName = findViewById(R.id.doctor_username);
        profileIcon = findViewById(R.id.doctor_profileIcon);

        userName.setText(user.getName());


        /*welcome=findViewById(R.id.textView4);
        Intent intent=getIntent();
        Users user= (Users) intent.getSerializableExtra("user");
        welcome.setText("hello"+user.getName());*/


        MaterialCardView card_view = (MaterialCardView) findViewById(R.id.profile_card_1); // creating a CardView and assigning a value.

        MaterialCardView doctorAppointment = (MaterialCardView) findViewById(R.id.profile_card_2);

        floatingActionButton=findViewById(R.id.addNewservice);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        floatingActionButton.setAnimation(animation);
        /**
         * doctor see his service
         * */
        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Doctor.this, DoctorService.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        /**
         * doctor see his/her service
         * */
        doctorAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Doctor.this, DoctorAppointment.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });



        /**
         * Get random image from calling api https://picsum.photos/200/300
         * */
        String url="";
        ImageRequest imageRequest=new ImageRequest("https://picsum.photos/id/" + String.valueOf(user.getUser_id()) + "/300/300", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                profileIcon.setImageBitmap(response);
            }
        }, 300, 300, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Doctor.this,"something wrong",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(Doctor.this).addRequest(imageRequest);

       /* RequestQueue requestQueue= Volley.newRequestQueue(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="";
                ImageRequest imageRequest=new ImageRequest("https://picsum.photos/200/300", new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        profileIcon.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Doctor.this,"something wrong",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
                MySingleton.getInstance(Doctor.this).addRequest(imageRequest);
            }
        });*/

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(Doctor.this, DoctorAddService.class);
                intent.putExtra("user", user);
                startActivity(intent);*/
               /* AlphaAnimation animation1 = new AlphaAnimation(1, 0);
                animation1.setDuration(1000);

                animation1.setFillAfter(true);
                floatingActionButton.startAnimation(animation1);*/
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                floatingActionButton.setAnimation(animation);
                MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(Doctor.this);
                dialogBuilder.setTitle("Add a new service");

                View viewInflated = LayoutInflater.from(Doctor.this).inflate(R.layout.activity_doctor_add_service, (ViewGroup) findViewById(android.R.id.content), false);

                dialogBuilder.setView(viewInflated);

                TextInputEditText service_name = (TextInputEditText) viewInflated.findViewById(R.id.service_add_name);
                TextInputEditText service_desc = (TextInputEditText) viewInflated.findViewById(R.id.service_add_desc);

                //dialogBuilder.setMessage("This is the message");
                dialogBuilder.setIcon(R.drawable.ic_baseline_post_add_24);
                dialogBuilder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg, null));

                dialogBuilder.setPositiveButton("Add Service", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        db = new DBHelper(getApplicationContext());

                        String serviceNameLabel = service_name.getText().toString();
                        String descriptionLabel = service_desc.getText().toString();
                        User user = (User) intent.getSerializableExtra("user");

                        if (serviceNameLabel.length() <= 0 || descriptionLabel.length() <= 0) {
                            Toast.makeText(Doctor.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                        } else {
                            Services services = new Services(serviceNameLabel, descriptionLabel);


                            db.insertServices(services, user.getUser_id());
                            Toast.makeText(Doctor.this, "done", Toast.LENGTH_SHORT).show();

                            /// finish();
                        }
                    }




                });

                dialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                dialogBuilder.show();

            }
        });

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Doctor.this, Profile.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

    }


}