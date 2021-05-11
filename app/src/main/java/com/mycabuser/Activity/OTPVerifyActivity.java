package com.mycabuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mycabuser.R;
import com.mycabuser.databinding.ActivityOTPVerifyBinding;

public class OTPVerifyActivity extends AppCompatActivity {
    ActivityOTPVerifyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOTPVerifyBinding.inflate(getLayoutInflater());
        setContentView( binding.getRoot());

        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OTPVerifyActivity.this, NavigationActivity.class));
            }
        });
    }
}