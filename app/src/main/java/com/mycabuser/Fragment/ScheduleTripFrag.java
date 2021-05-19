package com.mycabuser.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycabuser.R;
import com.mycabuser.databinding.FragmentScheduleTripBinding;


public class ScheduleTripFrag extends Fragment {

  FragmentScheduleTripBinding binding;
  private Context context;
  private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentScheduleTripBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram_container,new BottomPoularFragment()).commit();
            }
        });
       return view;
    }
}