package ali.app.doctorappointmentapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    List<User> user;
    Context context;
    DBHelper db;

    public UserAdapter(List<User> user, Context context) {
        this.user = user;
        this.context = context;

        db = new DBHelper(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_item_list, parent, false);


        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User userAdapter = user.get(position);
        holder.userList_name.setText(userAdapter.getName());
        holder.userList_email.setText(userAdapter.getEmail());
    }


    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText userList_name, userList_email;
        private Button update_user, delete_user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userList_name = itemView.findViewById(R.id.editText_name);
            userList_email = itemView.findViewById(R.id.editText_email);
           /* update_user=itemView.findViewById(R.id.updateUser);
            delete_user=itemView.findViewById(R.id.deleteUser);*/
        }
    }

}
