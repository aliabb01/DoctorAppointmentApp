package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    ConstraintLayout parent;
    EditText name, password, repPassword, email, message;
    private Spinner userlist1;
    Button signupButton, singInBtn;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.signUp_username);
        password = findViewById(R.id.signUp_password);
        repPassword = findViewById(R.id.signUp_pwConf);
        signupButton = findViewById(R.id.signUp_btnSubmit);
        singInBtn = findViewById(R.id.signUp_btnSignin);
        email = findViewById(R.id.signUp_email);
        userlist1 = findViewById(R.id.signUp_usertype);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rep = repPassword.getText().toString();
                String pas = password.getText().toString();
                try {
                    db = new DBHelper(getApplicationContext());
                    Users user = new Users();
                    user.setName(name.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setRole(userlist1.getSelectedItem().toString());
                    boolean checkName = db.checkusername(name.getText().toString());
                    if (checkName == false) {
                        if (db.insertData(user) != null) {
                            if (rep.equals(pas)) {
                                Toast.makeText(SignUp.this, "account created successfuly", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(SignUp.this, MainActivity.class);
                                startActivity(intent1);
                            } else {
                                Toast.makeText(SignUp.this, "the password not match", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(SignUp.this, "can not create", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "the name already exist", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("error");
                    builder.setMessage(e.getMessage());
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                }

            }
        });

        singInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
            }
        });


    }
}