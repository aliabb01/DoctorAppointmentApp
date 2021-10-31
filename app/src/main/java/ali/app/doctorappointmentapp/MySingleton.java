package ali.app.doctorappointmentapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private  static MySingleton instance;
    private RequestQueue requestQueue;
    private static Context mcontext;
    private MySingleton(Context context){
        mcontext=context;
        requestQueue=getRequestQueue();
    }

    public  RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return  requestQueue;
    }
    public  static  synchronized  MySingleton getInstance(Context context){
        if(instance ==null){
            instance=new MySingleton(context);
        }
        return instance;
    }
    public  <T> void addRequest(Request<T> request){
        requestQueue.add(request);
    }




























}
