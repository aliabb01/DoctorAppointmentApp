package ali.app.doctorappointmentapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class DoctorAppointmentAdapter extends RecyclerView.Adapter<DoctorAppointmentAdapter.ViewHolder>{
    List<Appointment> appointments;
    Context context;
    DBHelper db;

    public DoctorAppointmentAdapter(List<Appointment> appointments, Context context) {
        this.appointments = appointments;
        this.context = context;

        db = new DBHelper(context);
    }


    @NonNull
    @Override
    public DoctorAppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.doctor_appointment_list, parent, false);
        DoctorAppointmentAdapter.ViewHolder viewHolder = new DoctorAppointmentAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Appointment HistoryRecycle  = appointments.get(position);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        holder.doctor_date.setText(simpleDateFormat.format(HistoryRecycle.getDate()));
        holder.doctor_time.setText(HistoryRecycle.getTime().toString());
      //  holder.doctor_user.setText(String.valueOf(HistoryRecycle.getUser_id()).toString());
        String user=db.getuserName(HistoryRecycle.getUser_id());
        holder.doctor_user.setText(user);
        holder.treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///dialog to add treatment of user
            }
        });
    }





    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView doctor_date,doctor_time,doctor_user;
        Button treatment;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            doctor_date = itemView.findViewById(R.id.doctor_date_appointment);
            doctor_time=itemView.findViewById(R.id.doctor_time_appointment);
            doctor_user=itemView.findViewById(R.id.doctor_user_appointment);
            treatment=itemView.findViewById(R.id.treatment);
        }
    }
}
