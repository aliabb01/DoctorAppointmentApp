package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    EditText userSignin, passwordSignin;
    Spinner userlist;
    Button signinButton, signUpBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        userSignin = findViewById(R.id.nameUser);
        passwordSignin = findViewById(R.id.password);
        signinButton = findViewById(R.id.signIn_btnSignIn);
        signUpBtn = findViewById(R.id.signIn_btnSignUp);

        db = new DBHelper(this);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userlogin = userSignin.getText().toString();
                String pass = passwordSignin.getText().toString();
                db = new DBHelper(getApplicationContext());
                Users user = db.checkusernamepassword(userlogin, pass);
                if (userlogin.equals("") || pass.equals("")) {
                    Toast.makeText(SignIn.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {

                    if (user == null) {
                        Toast.makeText(SignIn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignIn.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        String checkRole = db.checkusernamepasswordrole(userlogin, pass);
                        //Log.d("myTag", "onClick: CHECKROLE IS: " + checkRole);
                        if (checkRole != "") {
                            //String na= db.seedetails(user);

                            // Log.d("myTag", "onClick: ID: " + na);
                            switch (checkRole) {
                                case "Patient":
                                    Intent intent = new Intent(SignIn.this, Home.class);
                                    intent.putExtra("user", user);

                                    startActivity(intent);
                                    break;
                                case "Doctor":
                                    Intent intentDoc = new Intent(SignIn.this, Doctor.class);
                                    intentDoc.putExtra("user", user);
                                    startActivity(intentDoc);
                                    break;
                                case "Admin":
                                    Intent intent1 = new Intent(SignIn.this, Admin.class);
                                    intent1.putExtra("user", user);
                                    startActivity(intent1);
                                    break;
                                default:
                                    Toast.makeText(SignIn.this, "no no ", Toast.LENGTH_SHORT).show();
                                    break;
                            }


                            Toast.makeText(SignIn.this, "Welcome " + user, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignIn.this, "please check your role", Toast.LENGTH_SHORT).show();

                        }

                    }
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });


    }

}