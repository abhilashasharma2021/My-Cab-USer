package com.mycabuser.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mycabuser.Fragment.ConfirmFrag;
import com.mycabuser.Fragment.HomeFragment;
import com.mycabuser.MainActivity;
import com.mycabuser.R;
import com.mycabuser.Utils.ProgressBarCustom.Appconstant;
import com.mycabuser.Utils.ProgressBarCustom.SharedHelper;

public class NavigationActivity extends AppCompatActivity  implements View.OnClickListener {
    public static DrawerLayout drawer;
    RelativeLayout rlLogout,rlSetting,rlNotification,rlHome,rlHistory;
    String REQUESTSTATUSPAGE="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        drawer = findViewById(R.id.drawer);
        REQUESTSTATUSPAGE=SharedHelper.getKey(getApplicationContext(),Appconstant.REQUESTSTATUSPAGE);

        Log.e("dvdsvsdvd", "REQUESTSTATUSPAGE: " +REQUESTSTATUSPAGE);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


        }

        rlLogout = findViewById(R.id.rlLogout);
        rlHome = findViewById(R.id.rlHome);
        rlSetting = findViewById(R.id.rlSetting);
        rlHistory = findViewById(R.id.rlHistory);
        rlLogout = findViewById(R.id.rlLogout);
        rlNotification = findViewById(R.id.rlNotification);
        rlSetting.setOnClickListener(this);
        rlNotification.setOnClickListener(this);
        rlHome.setOnClickListener(this);
        rlHistory.setOnClickListener(this);
        rlLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rlLogout:
               logout();
                break;

            case R.id.rlSetting:
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.rlNotification:
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.rlHistory:
                startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.rlHome:
             /*  getSupportFragmentManager().beginTransaction().replace(R.id.fram_container,new HomeFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);*/
                break;
           /* case R.id.rel_Trip:
                startActivity(new Intent(getApplicationContext(), TripHistoryActivity.class));
                drawer.closeDrawer(GravityCompat.START);
                break;







            case R.id.rlEarning:
                startActivity(new Intent(getApplicationContext(), EarningActivity.class));
                drawer.closeDrawer(GravityCompat.START);
                break;*/

         /*   case R.id.rel_mcworld:
                Intent intent = new Intent(Intent.ACTION_SEND);
                String[] recipients = {"sales@mcworld25.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject text here...");
                intent.putExtra(Intent.EXTRA_TEXT, "Your Message here...");
                intent.putExtra(Intent.EXTRA_CC, "mailcc@gmail.com");
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));
                drawer.closeDrawer(GravityCompat.START);
                break;*/
        }
    }
    public void logout() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.logout_dialog);
        Button btn_yes = dialog.findViewById(R.id.btn_yes);
        Button btn_no = dialog.findViewById(R.id.btn_no);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedHelper.putKey(getApplicationContext(), Appconstant.USERID, "");
                startActivity(new Intent(getApplicationContext(), SplashActivity.class));
                finish();
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
}