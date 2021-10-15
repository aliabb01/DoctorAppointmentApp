package ali.app.doctorappointmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ServicesList extends AppCompatActivity {

    RecyclerView rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);
        rec = findViewById(R.id.serviceListRec);

        rec.setLayoutManager(new GridLayoutManager(this, 2));
        // recyclerView.setHasFixedSize(true);

        DBHelper db=new DBHelper(this);
        List<Services> servicesModel=db.getServiceList();
        if(servicesModel.size()>0) {
            ServicesAdapter servicesAdapter=new ServicesAdapter(servicesModel, ServicesList.this);
            rec.setAdapter(servicesAdapter);

        }
        else {
            Toast.makeText(this, "no item", Toast.LENGTH_SHORT).show();
        }
    }
}