package ali.app.doctorappointmentapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Loading {
    CardView loading;
    ConstraintLayout constraintLayout;
    TextView message;
    ProgressBar  progressBar;
      Activity activity;
      AlertDialog alertDialog;
    Loading(Activity myactivity){
        activity=myactivity;


    }
    void buttonActivied(){

        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_progress,null));
        builder.setCancelable(false);
        alertDialog=builder.create();
        alertDialog.show();



}
    void buttondone(){

     alertDialog.dismiss();
    }


}
