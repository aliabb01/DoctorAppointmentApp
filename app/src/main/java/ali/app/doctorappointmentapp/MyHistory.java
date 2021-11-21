package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MyHistory extends AppCompatActivity {

    RecyclerView recycle_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_history);
        recycle_history=findViewById(R.id.recycle_history);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        /**
         * display the user appointment
         * */
        recycle_history.setLayoutManager(new LinearLayoutManager(this));
        recycle_history.setHasFixedSize(true);
        DBHelper db=new DBHelper(this);
        List<Appointment> servicesModel=db.getappointment(user.getUser_id());
        if(servicesModel.size()>0) {
            HistoryRecycle historyRecycle=new HistoryRecycle(servicesModel, MyHistory.this);
            recycle_history.setAdapter(historyRecycle);

        }
        else {
            Toast.makeText(this, "no item", Toast.LENGTH_SHORT).show();
        }
    }
}