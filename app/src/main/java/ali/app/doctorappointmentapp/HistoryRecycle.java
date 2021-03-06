package ali.app.doctorappointmentapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class HistoryRecycle extends RecyclerView.Adapter<HistoryRecycle.ViewHolder> {
    List<Appointment> appointments;
    Context context;
    String user;

    DBHelper db;

    public HistoryRecycle(List<Appointment> appointments, Context context) {
        this.appointments = appointments;
        this.context = context;

        db = new DBHelper(context);
    }


    @NonNull
    @Override
    public HistoryRecycle.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.history_page_item, parent, false);


        HistoryRecycle.ViewHolder viewHolder = new HistoryRecycle.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Intent intent = ((Activity)context).getIntent();
        Services services = (Services) intent.getSerializableExtra("service");
        final Appointment HistoryRecycle  = appointments.get(position);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        holder.history_date.setText(simpleDateFormat.format(HistoryRecycle.getDate()));
        holder.history_time.setText(HistoryRecycle.getTime().toString());
        holder.history_service.setText(String.valueOf(HistoryRecycle.getId()));

        String val = db.getTreatmentDesc(HistoryRecycle.getId());
        try {
            holder.treatmentDesc__recycle.setText(val);
        }
        catch(Exception e) {
            val=null;
            holder.treatmentDesc__recycle.setText("No treatment yet");
        }

        int value=db.getAppointmentId(HistoryRecycle.getId());

        String serviceName = db.getServicehistory(value);
        holder.history_servicename.setText(serviceName);

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.cancelappointment(HistoryRecycle.getId());
                Log.d("HIST", "HISTORYYYY: "+HistoryRecycle.getId());
                appointments.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context.getApplicationContext(),"the record have deleted successfully",Toast.LENGTH_SHORT ).show();
            }
        });

    }






    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView history_time,history_date,history_service,history_servicename, treatmentDesc__recycle;
        Button cancel;

        private Button book;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            history_date=itemView.findViewById(R.id.history_date);
            history_time=itemView.findViewById(R.id.history_time);
            history_service=itemView.findViewById(R.id.history_service);
            history_servicename=itemView.findViewById(R.id.history_servicename);
            treatmentDesc__recycle = itemView.findViewById(R.id.treatmentDesc__recycle);
            cancel=itemView.findViewById(R.id.cancel);
        }
    }
}
