package com.mycabuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mycabuser.R;
import com.mycabuser.Utils.ProgressBarCustom.Api;
import com.mycabuser.Utils.ProgressBarCustom.Appconstant;
import com.mycabuser.Utils.ProgressBarCustom.CustomDialog;
import com.mycabuser.Utils.ProgressBarCustom.SharedHelper;
import com.mycabuser.databinding.ActivityOTPVerifyBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class OTPVerifyActivity extends AppCompatActivity {
    ActivityOTPVerifyBinding binding;
    String getEmail="",getOTP="",getMobile="",pin,stUserID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOTPVerifyBinding.inflate(getLayoutInflater());
        setContentView( binding.getRoot());

        getEmail = SharedHelper.getKey(getApplicationContext(), Appconstant.UserEmail);
        getOTP = SharedHelper.getKey(getApplicationContext(), Appconstant.GetOtp);
        getMobile = SharedHelper.getKey(getApplicationContext(), Appconstant.UserMobile);
         stUserID = SharedHelper.getKey(getApplicationContext(), Appconstant.USERID);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.pinView.setAnimationEnable(true);


        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                pin = binding.pinView.getText().toString();



                if (pin.length() != 4) {

                    Toast.makeText(OTPVerifyActivity.this, "Please enter 4 digit otp", Toast.LENGTH_SHORT).show();

                } else {
                    Log.e("ksjldxks",pin);
                    if (getOTP.equals(pin)) {

                        verify_otp();


                    } else {
                        Toast.makeText(OTPVerifyActivity.this, "You have entered wrong otp", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });
    }


    private   void verify_otp(){

        Log.e("OTPVerifyActivity", "getEmail: " +getEmail);
        Log.e("OTPVerifyActivity", "getMobile: " +getMobile);
        Log.e("OTPVerifyActivity", "getOTP: " +getOTP);
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, this);
        AndroidNetworking.post(Api.BASE_URL+Api.verify_otp)
                .addBodyParameter("email",getEmail)
                .addBodyParameter("mobile",getMobile)
                .addBodyParameter("otp",getOTP)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("skclkxck",response.toString());
                        dialog.hideDialog();
                        try {
                            if (response.getString("result").equals("sign_in  Successfully")){
                                String data=response.getString("data");
                                JSONObject jsonObject=new JSONObject(data);
                                Log.e("OTPVerifyActivity", "status: " +jsonObject.getString("otp_varify_status"));

                             if (jsonObject.getString("otp_varify_status").equals("0")){
                                 signUp();
                                 Toast.makeText(OTPVerifyActivity.this, response.getString("result"), Toast.LENGTH_SHORT).show();
                             }
                             else {

                                 Log.e("OTPVerifyActivity", "login: " + jsonObject.getString("id"));
                                 SharedHelper.putKey(getApplicationContext(), Appconstant.UserEmail, jsonObject.getString("email"));
                                 SharedHelper.putKey(getApplicationContext(), Appconstant.USERID, jsonObject.getString("id"));
                                 SharedHelper.putKey(getApplicationContext(), Appconstant.UserMobile, jsonObject.getString("phone_number"));


                                 startActivity(new Intent(OTPVerifyActivity.this,NavigationActivity.class));
                                 Toast.makeText(OTPVerifyActivity.this, response.getString("result"), Toast.LENGTH_SHORT).show();
                             }


                            }
                            else {
                                Toast.makeText(OTPVerifyActivity.this, response.getString("result"), Toast.LENGTH_SHORT).show();
                                dialog.hideDialog();
                            }
                        } catch (JSONException e) {
                            Log.e("wrfwef",e.getMessage());
                            dialog.hideDialog();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("ewrgfdsg",anError.getMessage());
                        dialog.hideDialog();

                    }
                });



    }


    public void signUp() {


        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, this);
        AndroidNetworking.post(Api.BASE_URL+Api.signup)
                .addBodyParameter("email", getEmail)
                .addBodyParameter("mobile", getMobile)
                .addBodyParameter("user_id", stUserID)
                .addBodyParameter("type", "1")/* type=0 Driver type= 1 user*/
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("grtgfnjgh", response.toString());
                        dialog.hideDialog();
                        try {
                            if (response.getString("result").equals("Signup Successfully")) {

                                Log.e("OTPVerifyActivity", "signup: " + response.getString("id"));
                                SharedHelper.putKey(getApplicationContext(), Appconstant.UserEmail, response.getString("email"));
                                SharedHelper.putKey(getApplicationContext(), Appconstant.USERID, response.getString("id"));
                                SharedHelper.putKey(getApplicationContext(), Appconstant.UserMobile, response.getString("phone_number"));



                                startActivity(new Intent(OTPVerifyActivity.this, NavigationActivity.class));
                                Toast.makeText(OTPVerifyActivity.this, response.getString("result"), Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(OTPVerifyActivity.this, response.getString("result"), Toast.LENGTH_SHORT).show();
                                dialog.hideDialog();
                            }
                        } catch (JSONException e) {
                            Log.e("sdgbvdfbh", e.getMessage());
                            dialog.hideDialog();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("gbftn", anError.getMessage());
                        dialog.hideDialog();

                    }
                });


    }
}