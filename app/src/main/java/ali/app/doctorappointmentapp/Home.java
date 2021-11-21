package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.android.material.imageview.ShapeableImageView;


import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    TextView welcome, welcome1, welcome2;
    private ImageView patient_profileIcon;
    private RecyclerView user_servcies;
   private AutoCompleteTextView autoComplete;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        welcome = (TextView) findViewById(R.id.welcome_user);
        patient_profileIcon=findViewById(R.id.patient_profileIcon);
        autoComplete=findViewById(R.id.autoComplete);
        user_servcies=findViewById(R.id.user_servcies);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        welcome.setText(user.getName());
        /**
         * call loadResult method to display the result in the autocompletetext
         * */
        loadResult();
       /**
        * Add the service Adapter from servcieAdapter class and check if its not empty
        * */
        user_servcies.setLayoutManager(new LinearLayoutManager(this));
        user_servcies.setHasFixedSize(true);
        DBHelper db=new DBHelper(this);
        List<Services> servicesModel=db.getServiceList();

        if(servicesModel.size()>0) {
            ServicesAdapter servicesAdapter=new ServicesAdapter(servicesModel,Home.this);
            user_servcies.setAdapter(servicesAdapter);


        }
        else {
            Toast.makeText(this, "no item", Toast.LENGTH_SHORT).show();
        }




        /**
         * GET randrom image from picsum Api
         * */
        String url="";
        ImageRequest imageRequest=new ImageRequest("https://picsum.photos/id/" + String.valueOf(user.getUser_id()) + "/300/300", new Response.Listener<Bitmap>() {
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
        /**
         * lOadResult from searchAdapter class
         * * */
   public  void loadResult(){
        List<Services> services=new ArrayList<Services>();
        SearchAdapter searchAdapter=new SearchAdapter(getApplicationContext(),services);
      //  autoComplete.setThreshold(1);
        autoComplete.setAdapter(searchAdapter);

   }
}