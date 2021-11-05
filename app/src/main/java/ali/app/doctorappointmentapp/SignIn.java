package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    EditText userSignin, passwordSignin;
    Spinner userlist;
    Button signinButton, signUpBtn;
    DBHelper db;

    ConstraintLayout signInLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInLayout = (ConstraintLayout)findViewById(R.id.signInLayout);

        userSignin = findViewById(R.id.nameUser);
        passwordSignin = findViewById(R.id.password);
        signinButton = findViewById(R.id.signIn_btnSignIn);
        signUpBtn = findViewById(R.id.signIn_btnSignUp);

        db = new DBHelper(this);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Loading loading=new Loading(SignIn.this);
                String userlogin = userSignin.getText().toString();
                String pass = passwordSignin.getText().toString();
                db = new DBHelper(getApplicationContext());
                User user = db.checkusernamepassword(userlogin, pass);
                if (userlogin.equals("") || pass.equals("")) {
                    Toast.makeText(SignIn.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {

                    if (user == null) {
                        Toast.makeText(SignIn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignIn.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        String checkRole = db.checkusernamepasswordrole(userlogin, pass);
                        loading.buttonActivied();
                        final Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loading.buttondone();
                                Handler handler1=new Handler();
                                handler1.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
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


                                            Toast.makeText(SignIn.this, "Welcome " + user.getName(), Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(SignIn.this, "please check your role", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                },0);

                            }
                        },300);
                        //Log.d("myTag", "onClick: CHECKROLE IS: " + checkRole);


                    }
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Close keyboard on form submit
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }

                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });


    }

}