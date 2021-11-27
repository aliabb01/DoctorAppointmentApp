package ali.app.doctorappointmentapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
public class UpdateAccount extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView updatetest;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        recyclerView = findViewById(R.id.update_recycle);
        db=new DBHelper(this);

        Intent intent=getIntent();
        User user= (User) intent.getSerializableExtra("user");
///        updatetest.setText(user.getName());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<User> usersmodel=db.getUserDetails(user.getUser_id());
        if(usersmodel.size()>0) {
            UpdateAccountAdapter updateAccountAdapter=new UpdateAccountAdapter(usersmodel, UpdateAccount.this);
            recyclerView.setAdapter(updateAccountAdapter);
        }
        else {
            Toast.makeText(this, "no item", Toast.LENGTH_SHORT).show();
        }


      /*  updaterecycle = findViewById(R.id.recycle_serviceDoctor);
        db = new DBHelper(this);
        //  String name=intent.getStringExtra("user");
        updaterecycle.setLayoutManager(new LinearLayoutManager(this));
        User user= (User) intent.getSerializableExtra("user");
        DBHelper db=new DBHelper(this);
        List<User> servicesModel=db.getUserDetails(user.getUser_id());
        if(servicesModel.size()>0) {
            UpdateAccountAdapter updateAccountAdapter=new UpdateAccountAdapter(servicesModel, UpdateAccount.this);
            updaterecycle.setAdapter(updateAccountAdapter);
        }
        else {
            Toast.makeText(this, "no item", Toast.LENGTH_SHORT).show();
        }*/
    }
}