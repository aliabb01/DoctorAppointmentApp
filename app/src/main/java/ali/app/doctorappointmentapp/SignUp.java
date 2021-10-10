package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SignUp extends AppCompatActivity {
    ConstraintLayout parent;
    EditText name,password,repPassword,email,message;
    private Spinner userlist1;
    Button signupButton;

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=findViewById(R.id.userName);
        password=findViewById(R.id.signUppassword);
        repPassword=findViewById(R.id.passwordConform);
        signupButton=findViewById(R.id.signUpButton);
        email=findViewById(R.id.eamil);
        message=findViewById(R.id.message);
        userlist1=findViewById(R.id.spinner2);

        db = new DBHelper(this);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = name.getText().toString();
                String pass = password.getText().toString();
                String repass = repPassword.getText().toString();
                String userEmail=email.getText().toString();
                String  userrole=userlist1.getSelectedItem().toString();

                if(user.equals("") || pass.equals("") || repass.equals("")||userEmail.equals("")) {
                    Toast.makeText(SignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                //    Snackbar.make(parent,"Please fill the files",Snackbar.LENGTH_SHORT).show();
                ///  message.setVisibility(View.VISIBLE);
                //  message.setText("Please fill the Fields");}
                    /* if(user.equals("")){
                    name.setText("please fill the files");
                    name.setTextColor(Color.rgb(255,0,0));
                }else if(pass.equals("")){
                    password.setText("please fill the files");
                    password.setTextColor(Color.rgb(255,0,0));
                }else if(repass.equals("")){
                    repPassword.setText("please fill the files");
                    repPassword.setTextColor(Color.rgb(255,0,0));
                }else if(userEmail.equals("")){
                    email.setText("please fill the files");
                    email.setTextColor(Color.rgb(255,0,0));
                }*/


                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = db.checkusername(user);
                        if(checkuser==false) {
                            Boolean insert = db.insertData(user,userEmail ,pass,userrole);
                            if(insert==true){
                                Toast.makeText(SignUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(SignUp.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}