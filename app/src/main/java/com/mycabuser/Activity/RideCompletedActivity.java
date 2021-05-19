package com.mycabuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mycabuser.R;
import com.mycabuser.databinding.ActivityRatingBinding;
import com.mycabuser.databinding.ActivityRideCompletedBinding;

public class RideCompletedActivity extends AppCompatActivity {
ActivityRideCompletedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRideCompletedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}