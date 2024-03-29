package com.mycabuser.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.navigation.NavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mycabuser.Fragment.ConfirmFrag;
import com.mycabuser.Fragment.HomeFragment;
import com.mycabuser.MainActivity;
import com.mycabuser.R;
import com.mycabuser.Utils.ProgressBarCustom.Api;
import com.mycabuser.Utils.ProgressBarCustom.Appconstant;
import com.mycabuser.Utils.ProgressBarCustom.SharedHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.mycabuser.Utils.ProgressBarCustom.Appconstant.USERID;

public class SplashActivity extends AppCompatActivity {
    String userId = "", PagerStatus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       /* final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 5000);*/


        userId = SharedHelper.getKey(getApplicationContext(), USERID);

        Log.e("fdhfghgfh", "USERID: " +USERID);

        PagerStatus = SharedHelper.getKey(getApplicationContext(), Appconstant.PagerStatus);

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE

                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (PagerStatus.equals("1")) {
                    if (userId.equals("")) {
                        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));

                    } else {
                        show_driver_responce();
                    }
                }else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                }



            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();


    }


    /*1,4,6,5--screen change*/

    /*0=user_book,1=driver_confirm 2=driver_cancle 3=user_cancle 4=userc_confirm , 6=complete ride 5= start ride*/
    private void show_driver_responce() {
        AndroidNetworking.post(Api.BASE_URL + Api.show_driver_responce)
                .addBodyParameter("user_id", userId)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("fdghfdhbfg", "response: " +response);
                        try {
                            String driver_status = response.getString("driver_status");/*driver_status=true...driver_status=false  */
                            String status = response.getString("status");

                            if (driver_status.equals("true")) {

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {


                                        if (PagerStatus.equals("1")) {
                                            if (userId.equals("")) {
                                                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                                                SharedHelper.putKey(getApplicationContext(), Appconstant.REQUESTSTATUSPAGE, "");
                                            } else {

                                                if (status.equals("1")) {
                                                    SharedHelper.putKey(getApplicationContext(), Appconstant.REQUESTSTATUSPAGE, "1");
                                                    startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                                                    finish();
                                                    Log.e("sdssdsdsds", status + "1");
                                                } else {

                                                    SharedHelper.putKey(getApplicationContext(), Appconstant.REQUESTSTATUSPAGE, "");
                                                    startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                                                    finish();
                                                    Log.e("sdssdsdsds", status + "ccc");
                                                }

                                            }


                                        } else {

                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            finish();
                                        }


                                    }
                                }, 3000);


                            }else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }
}






