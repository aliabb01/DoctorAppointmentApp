package ali.app.doctorappointmentapp;

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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {
    List<Services> service;
    Context context;
    DBHelper db;

    public ServicesAdapter(List<Services> service, Context context) {
        this.service = service;
        this.context = context;

        db = new DBHelper(context);
    }


    @NonNull
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.service_list_item, parent, false);


        ServicesAdapter.ViewHolder viewHolder = new ServicesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.ViewHolder holder, int position) {
        final Services serviceAdapter = service.get(position);
        holder.name.setText(serviceAdapter.getName());
        holder.desc.setText(serviceAdapter.getDescription());
    }


    @Override
    public int getItemCount() {
        return service.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, desc;
        private Button update_user, delete_user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.admin_serviceName);
            desc = itemView.findViewById(R.id.admin_serviceDescription);
        }
    }
}
