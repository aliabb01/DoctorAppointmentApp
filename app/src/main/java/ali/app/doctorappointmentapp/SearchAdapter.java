package ali.app.doctorappointmentapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<Services> {
    private Context context;
    private  int last=5;
    private  List<Services>services=new ArrayList<Services>();

    /**
     * constructor of searchAdapter and call it in the Home.jave
     * */
    public SearchAdapter( Context context,List<Services> services) {
        super(context,R.layout.search_item,services);
        this.context = context;
        this.services=services;

    }
    /**
     * set the min item can be display when we get the result
     * */
    @Override
    public int getCount() {
        return Math.min(last,services.size());
    }
    /**
     * user layoutInflator to create a new layout to display result
     * */
    // TODO: fix the design of search_item.xml
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item,null);
        Services service=services.get(position);
        Intent intent =((Activity) context).getIntent();
        User user = (User) intent.getSerializableExtra("user");
        TextView textView=view.findViewById(R.id.textView10);
        textView.setText(service.getName());
        TextView textView1=view.findViewById(R.id.textView11);
        textView1.setText(service.getDescription());
        CardView search_card=view.findViewById(R.id.search_card);
        search_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ServicePage.class);
                Bundle extras = new Bundle();
                extras.putString("service", service.getId() + "");
                intent.putExtras(extras);
                intent.putExtra("user",user);
                context.startActivity(intent);
            }
        });
        return view;
    }
    @NonNull
    @Override
    public Filter getFilter() {
        return new serviceFilter(this,context);
    }
    private  class  serviceFilter extends  Filter{
    private  SearchAdapter searchAdapter;
    private  Context context;

    public  serviceFilter(SearchAdapter searchAdapter, Context context){
       super();
       this.searchAdapter=searchAdapter;
       this.context=context;

   }

      /**
       * in performFiltering we check if the charchter is incrase and not empty and take value from the ArrayList
       * **/

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            searchAdapter.services.clear();
           FilterResults filterResults=new FilterResults();
           if(charSequence == null || charSequence.length()==0){
               filterResults.values=new ArrayList<Services>();
               filterResults.count=0;
           }

           else{
               DBHelper db=new DBHelper(context);
               List<Services>services=db.search(charSequence.toString());
               filterResults.values=services;
               filterResults.count=services.size();
           }
            return filterResults;
        }
        /***
         * in this function we check if the filterresult value not null to avoid get error otherwise we got the item from
         * the list
         * */
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
               searchAdapter.services.clear();
               if(filterResults.values == null){
                    Toast.makeText(context,"No such service",Toast.LENGTH_SHORT).show();
               }else {
                   searchAdapter.services.addAll((List<Services>) filterResults.values);
                   searchAdapter.notifyDataSetChanged();
               }
   }

        @Override
        public CharSequence convertResultToString(Object resultValue) {

          Services service=(Services)resultValue;
          return  service.getName();
        }

    }















}
