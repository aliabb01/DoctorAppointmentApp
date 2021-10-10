package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
        name=findViewById(R.id.signUp_username);
        password=findViewById(R.id.signUp_password);
        repPassword=findViewById(R.id.signUp_pwConf);
        signupButton=findViewById(R.id.signUp_btnSubmit);
        email=findViewById(R.id.signUp_email);
        userlist1=findViewById(R.id.signUp_usertype);

        db = new DBHelper(this);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = name.getText().toString();
                String pass = password.getText().toString();
                String repass = repPassword.getText().toString();
                String userEmail=email.getText().toString();
                String userrole=userlist1.getSelectedItem().toString();

                if(user.equals("") || pass.equals("") || repass.equals("")||userEmail.equals("")) {
                    Toast.makeText(SignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }


                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = db.checkusername(user);
                        if(checkuser==false) {
                            // if values are inserted
                            Boolean insert = db.insertData(user, userEmail, pass,userrole);
                            if(insert){
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