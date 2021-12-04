package ali.app.doctorappointmentapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class DoctorServicesAdapter extends RecyclerView.Adapter<DoctorServicesAdapter.ViewHolder> {
    List<Services> service;
    Context context;
    DBHelper db;

    public DoctorServicesAdapter(List<Services> service, Context context) {
        this.service = service;
        this.context = context;

        db = new DBHelper(context);
    }


    @NonNull
    @Override
    public DoctorServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.doctor_service_list, parent, false);
       ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Services DoctorServicesAdapter = service.get(position);
        holder.textView4.setText(Integer.toString(DoctorServicesAdapter.getId()));
        holder.name.setText(DoctorServicesAdapter.getName());
        holder.desc.setText(DoctorServicesAdapter.getDescription());
        holder.delete_servcie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        db.deleteService(DoctorServicesAdapter.getId());
        service.remove(position);
        notifyDataSetChanged();
                Toast.makeText(view.getContext(),"deleted successfully",Toast.LENGTH_SHORT).show();
               /* if(db.deleteService(DoctorServicesAdapter.getId())==true)
                {
                    Toast.makeText(view.getContext(),"deleted successfully",Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(view.getContext(),"no",Toast.LENGTH_SHORT).show();
                }*/
              ///    Toast.makeText(view.getContext(),"deleted successfully",Toast.LENGTH_SHORT).show();
               ///   DBHelper myDbOBJ = new DBHelper(view.getContext());
               //   myDbOBJ.deleteService(DoctorServicesAdapter.getId()); // set Dynamic


                 // notifyDataSetChanged();
                  ///to refresh the page
                //  Intent intent1 = new Intent(context, DoctorServicesAdapter.class);
                 // context.startActivity(intent1);

            }
        });
        holder.update_servcie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   String serviceName=holder.name.getText().toString();
                   String serviceDescrption=holder.desc.getText().toString();
                   db.updateService(new Services(DoctorServicesAdapter.getId(),serviceName,serviceDescrption));
                Toast.makeText(view.getContext(),"Updated successfully",Toast.LENGTH_SHORT).show();
                  notifyDataSetChanged();
                ((Activity)context).finish();
            }
        });

    }



    @Override
    public int getItemCount() {
        return service.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText name, desc;
        TextView textView4;
        private Button update_servcie, delete_servcie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.doctorService_item);
            desc = itemView.findViewById(R.id.doctorService_item2);
            textView4 = itemView.findViewById(R.id.textView4);
            update_servcie = itemView.findViewById(R.id.updateService);
            delete_servcie = itemView.findViewById(R.id.deleteServcie);

        }
    }
}
