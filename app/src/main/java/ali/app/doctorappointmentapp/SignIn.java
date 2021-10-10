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
    EditText userSignin,passwordSignin;
    Spinner userlist;
    Button signinButton;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        userSignin=findViewById(R.id.nameUser);
        passwordSignin=findViewById(R.id.password);
        signinButton=findViewById(R.id.signIn__btnSignIn);
        db=new DBHelper(this);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userSignin.getText().toString();
                String pass = passwordSignin.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(SignIn.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = db.checkusernamepassword(user,pass);
                    if(checkuserpass == true) {
                        Toast.makeText(SignIn.this, "Sign in successfully", Toast.LENGTH_SHORT).show();


                       Integer checkRole=db.checkusernamepasswordrole(user, pass);
                       Log.d("myTag", "onClick: CHECKROLE IS: " + checkRole);
                       if(checkRole != -1){
                           //Toast.makeText(SignIn.this,"Welcome back",Toast.LENGTH_SHORT).show();
                           switch (checkRole){
                               case 0:
                                   Intent intent=new Intent(SignIn.this, Home.class);
                                   startActivity(intent);
                                   break;
                               case "Patient":
                                   Intent intent1=new Intent(SignIn.this, Home.class);
                                   startActivity(intent1);
                                   break;
                               default:
                                   Toast.makeText(SignIn.this, "no no ", Toast.LENGTH_SHORT).show();
                                   break;
                           }
                           Toast.makeText(SignIn.this,"Welcome " + user,Toast.LENGTH_SHORT).show();
                       }
                       else
                       {
                           Toast.makeText(SignIn.this,"please check your role",Toast.LENGTH_SHORT).show();

                       }

                    } else {
                        Toast.makeText(SignIn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }

}