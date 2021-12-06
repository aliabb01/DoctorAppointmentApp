
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
import android.widget.ImageView;
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
       // holder.password.setText(serviceAdapter.getPassword());
        holder.user_arrowdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.password.setVisibility(View.VISIBLE);
                holder.confirmUpdatePassword.setVisibility(View.VISIBLE);
                holder.textView28.setVisibility(View.VISIBLE);
                holder.user_arrowup.setVisibility(View.VISIBLE);
                holder.user_arrowdown.setVisibility(View.GONE);
            }
        });
        holder.user_arrowup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.password.setVisibility(View.GONE);
                holder.confirmUpdatePassword.setVisibility(View.GONE);
                holder.textView28.setVisibility(View.GONE);
                holder.user_arrowup.setVisibility(View.GONE);
                holder.user_arrowdown.setVisibility(View.VISIBLE);
            }
        });

        holder.confirm_update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name=holder.name.getText().toString();
                String email=holder.email.getText().toString();
                String password=holder.password.getText().toString();
                String repassword=holder.confirmUpdatePassword.getText().toString();
                boolean checkName = db.checkusername(holder.name.getText().toString());
                if (name.equals("") || email.equals("")) {
                    Toast.makeText(view.getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {

                if(password.equals(repassword)){
               
                db.updateUser(new User(name,email,password,usera.getUser_id()));
                Toast.makeText(view.getContext(),"Updated successfully",Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                // ((Activity)context).finish();
                Intent intent = new Intent(view.getContext(), SignIn.class);
                context.startActivity(intent);

                }else
                {
                    Toast.makeText(view.getContext(),"password not match",Toast.LENGTH_SHORT).show();

                }}
            }
        });
    }


    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText name,email,password,confirmUpdatePassword;
        TextView textView28;
        ImageView user_arrowdown,user_arrowup;
        Button confirm_update;

        TextView my;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userUpdate);
            email = itemView.findViewById(R.id.userEmail);
            password=itemView.findViewById(R.id.passwordUser);
            confirm_update=itemView.findViewById(R.id.confirm_update);
            user_arrowdown=itemView.findViewById(R.id.user_arrowdown);
            user_arrowup=itemView.findViewById(R.id.user_arrowup);
            textView28=itemView.findViewById(R.id.textView28);
            confirmUpdatePassword=itemView.findViewById(R.id.confirmUpdatePassword);
            my=itemView.findViewById(R.id.my);
           /* update_user=itemView.findViewById(R.id.updateUser);
            delete_user=itemView.findViewById(R.id.deleteUser);*/
        }
    }

}


