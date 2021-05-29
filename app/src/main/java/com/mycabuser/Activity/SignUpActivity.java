package com.mycabuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mycabuser.APIData.API;
import com.mycabuser.R;
import com.mycabuser.Utils.ProgressBarCustom.Api;
import com.mycabuser.Utils.ProgressBarCustom.Appconstant;
import com.mycabuser.Utils.ProgressBarCustom.CustomDialog;
import com.mycabuser.Utils.ProgressBarCustom.SharedHelper;
import com.mycabuser.databinding.ActivitySignUpBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    String stMobile = "", stEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String text = "By click start, you agree to our  <font color=#0092df>Terms and conditions</font>";
        binding.txtterm.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);

        binding.txSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expands(binding.card, 600, 800);
                binding.btnSignIn.animate().alpha((float) 1).setDuration(1000).start();
                binding.cardEmail.setVisibility(View.GONE);
                binding.txSignIn.setTextColor(getResources().getColor(R.color.black));
                binding.txSignUp.setTextColor(getResources().getColor(R.color.grey));
                binding.btnSignUp.setVisibility(View.GONE);
                binding.llIntegration.setVisibility(View.GONE);
                binding.rlTerm.setVisibility(View.GONE);
                binding.btnSignIn.setVisibility(View.VISIBLE);
                binding.txLogin.setVisibility(View.VISIBLE);
            }
        });


        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stMobile = binding.etMobile.getText().toString().trim();
                if (validationSignIn()) {
                    /*call api*/
                  //  Toast.makeText(SignUpActivity.this, "check", Toast.LENGTH_SHORT).show();
                    genrate_otp();
                } else {
                    validationSignIn();

                }
            }
        });


        binding.txSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collapse(binding.card, 600, 800);
                binding.btnSignUp.animate().alpha((float) 1).setDuration(1000).start();
                binding.cardEmail.setVisibility(View.VISIBLE);
                binding.txSignIn.setTextColor(getResources().getColor(R.color.grey));
                binding.txSignUp.setTextColor(getResources().getColor(R.color.black));
                binding.btnSignIn.setVisibility(View.GONE);
                binding.btnSignUp.setVisibility(View.VISIBLE);
                binding.llIntegration.setVisibility(View.VISIBLE);
                binding.rlTerm.setVisibility(View.VISIBLE);
                binding.btnSignIn.setVisibility(View.GONE);
                binding.txLogin.setVisibility(View.GONE);
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stMobile = binding.etMobile.getText().toString().trim();
                stEmail = binding.etEmail.getText().toString().trim();


                if (validation()) {
                    /*call api*/
                    if (!validateEmailAddress(stEmail)) {
                        Toast.makeText(SignUpActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    } else {
                        genrate_otp();
                    }

                } else {
                    validation();
                }
            }
        });
    }


    public static boolean validateEmailAddress(String stEmail) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(stEmail);
        return matcher.matches();
    }

    public static void expands(final View v, int duration, int targetHeight) {

        int prevHeight = v.getHeight();

        v.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }


    public static void collapse(final View v, int duration, int targetHeight) {
        int prevHeight = v.getHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    private Boolean validation() {

        Boolean boolen = false;
        if (binding.etEmail.getText().toString().isEmpty()) {

            binding.etEmail.setError("Email Address Must Required");
        } else if (binding.etMobile.getText().toString().isEmpty()) {

            binding.etMobile.setError("Mobile Number Must Required");
        } else if (stMobile.length() < 10) {

            binding.etMobile.setError("Please enter atleast 10 digit mobile number");
        } else {
            boolen = true;

        }
        return boolen;
    }


    private Boolean validationSignIn() {

        Boolean boolen = false;
        if (binding.etMobile.getText().toString().isEmpty()) {

            binding.etMobile.setError("Mobile Number Must Required");
        } else if (stMobile.length() < 10) {

            binding.etMobile.setError("Please enter atleast 10 digit mobile number");
        } else {
            boolen = true;

        }
        return boolen;
    }


    public void genrate_otp() {
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, this);


        Log.e("SignUpActivity", "stEmail: " + stEmail);
        Log.e("SignUpActivity", "stMobile: " + stMobile);
        AndroidNetworking.post(Api.BASE_URL + Api.genrate_otp)
                .addBodyParameter("email", stEmail)
                .addBodyParameter("mobile", stMobile)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("dsvdcxv", response.toString());
                        dialog.hideDialog();
                        try {
                            if (response.getString("result").equals("Otp Sent Successfully")) {


                                String phone_number = response.getString("phone_number");
                                String otp = response.getString("otp");
                                String email = response.getString("email");

                                Log.e("dsgfdg", "phone_number: " + phone_number);
                                SharedHelper.putKey(getApplicationContext(), Appconstant.USERID, response.getString("id"));
                                SharedHelper.putKey(getApplicationContext(), Appconstant.UserEmail, response.getString("email"));
                                SharedHelper.putKey(getApplicationContext(), Appconstant.UserMobile, response.getString("phone_number"));
                                SharedHelper.putKey(getApplicationContext(), Appconstant.GetOtp, response.getString("otp"));

                                Toast toast = Toast.makeText(SignUpActivity.this, response.getString("result"), Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                                startActivity(new Intent(SignUpActivity.this, OTPVerifyActivity.class));
                                finish();


                            } else {

                                Toast toast = Toast.makeText(SignUpActivity.this, response.getString("message"), Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                dialog.hideDialog();
                            }
                        } catch (JSONException e) {
                            dialog.hideDialog();
                            Log.e("gngfn", e.getMessage());
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("tbgfrng", anError.getMessage());
                        dialog.hideDialog();
                    }
                });


    }


}