package com.mycabuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mycabuser.R;
import com.mycabuser.databinding.ActivityMyAccountBinding;

public class MyAccountActivity extends AppCompatActivity {
ActivityMyAccountBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyAccountBinding.inflate(getLayoutInflater());
        setContentView( binding.getRoot());
    }
}