
        package ali.app.doctorappointmentapp;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class UpdateAccountAdapter extends RecyclerView.Adapter<UpdateAccountAdapter.ViewHolder> {
    List<User> user;
    Context context;
    DBHelper db;
    public UpdateAccountAdapter(List<User> user, Context context) {
        this.user = user;
        this.context = context;
        db = new DBHelper(context);
    }


    @NonNull
    @Override
    public UpdateAccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_item_update, parent, false);
        UpdateAccountAdapter.ViewHolder viewHolder = new UpdateAccountAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Intent intent = ((Activity)context).getIntent();
        User usera = (User) intent.getSerializableExtra("user");
        final User serviceAdapter = user.get(position);
        holder.my.setText(Integer.toString(usera.getUser_id()));
        holder.name.setText(serviceAdapter.getName());
        holder.email.setText(serviceAdapter.getEmail());
        holder.password.setText(serviceAdapter.getPassword());
        holder.confirm_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=holder.name.getText().toString();
                String email=holder.email.getText().toString();
                String password=holder.password.getText().toString();
                boolean checkName = db.checkusername(holder.name.getText().toString());
                if (checkName == false) {
                db.updateUser(new User(name,email,password,usera.getUser_id()));
                Toast.makeText(view.getContext(),"Updated successfully",Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                // ((Activity)context).finish();
                Intent intent = new Intent(view.getContext(), SignIn.class);
                context.startActivity(intent);
            }else
                {
                    Toast.makeText(view.getContext(),"name is already exist",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText name,email,password;
        Button confirm_update;
        TextView my;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userUpdate);
            email = itemView.findViewById(R.id.userEmail);
            password=itemView.findViewById(R.id.passwordUser);
            confirm_update=itemView.findViewById(R.id.confirm_update);
            my=itemView.findViewById(R.id.my);
           /* update_user=itemView.findViewById(R.id.updateUser);
            delete_user=itemView.findViewById(R.id.deleteUser);*/
        }
    }

}


