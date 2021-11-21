package ali.app.doctorappointmentapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServicePageAdapter extends RecyclerView.Adapter<ServicePageAdapter.ViewHolder> {
    List<Services> service;
    Context context;
    String user;

    DBHelper db;

    public ServicePageAdapter(List<Services> service, Context context) {
        this.service = service;
        this.context = context;

        db = new DBHelper(context);
    }
    public ServicePageAdapter(List<Services> service, String user ,Context context) {
        this.service = service;
        this.context = context;
         this.user=user;

        db = new DBHelper(context);
    }

    @NonNull
    @Override
    public ServicePageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.service_page_item, parent, false);


        ServicePageAdapter.ViewHolder viewHolder = new ServicePageAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Services ServicePageAdapter = service.get(position);

        holder.id.setText(ServicePageAdapter. getId().toString());
        holder.name.setText(ServicePageAdapter.getName());
        holder.desc.setText(ServicePageAdapter.getDescription());
        holder.servicepage_userid.setText((ServicePageAdapter.getUser_id().toString()));
        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }




    @Override
    public int getItemCount() {
        return service.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, desc,id,servicepage_userid;

        private Button book;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.servicepage_name);
            desc = itemView.findViewById(R.id.servicepage_description);
             id = itemView.findViewById(R.id.servicepage_id);
          servicepage_userid=itemView.findViewById(R.id.servicepage_userid);
            book=itemView.findViewById(R.id.books);
        }
    }
}
