package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class userList extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        recyclerView=findViewById(R.id.recylceView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // recyclerView.setHasFixedSize(true);

        DBHelper db=new DBHelper(this);
        List<Users> usersmodel=db.getuserList();
        if(usersmodel.size()>0){
            UserAdapter usersadapter=new UserAdapter(usersmodel,userList.this);
            recyclerView.setAdapter(usersadapter);

        }else
        {
            Toast.makeText(this, "no item", Toast.LENGTH_SHORT).show();
        }
    }
}