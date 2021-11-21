package ali.app.doctorappointmentapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {
    List<Services> service;
    User user=new User();
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
        /**
         * pass the user and service id to the service page
         * */
        Intent intent = ((Activity)context).getIntent();
        User user = (User) intent.getSerializableExtra("user");

        final Services serviceAdapter = service.get(position);
        holder.id.setText(serviceAdapter. getId().toString());
        holder.name.setText(serviceAdapter.getName());
        holder.desc.setText(serviceAdapter.getDescription());
      holder.service_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), ServicePage.class);
                Bundle extras = new Bundle();
                extras.putString("service", serviceAdapter.getId() + "");
                intent.putExtras(extras);
                intent.putExtra("user",user);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return service.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, desc,id;
        private CardView service_item;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.admin_serviceName);
            desc = itemView.findViewById(R.id.admin_serviceDescription);
            id = itemView.findViewById(R.id.id);
            service_item=itemView.findViewById(R.id.service_item);
        }
    }
}
