package com.mycabuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.mycabuser.R;
import com.mycabuser.databinding.ActivityRatingBinding;

public class AddRatingActivity extends AppCompatActivity {
ActivityRatingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityRatingBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());


    }
}