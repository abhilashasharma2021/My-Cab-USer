package com.mycabuser.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycabuser.R;
import com.mycabuser.databinding.FragmentRequestBinding;


public class RequestFragment extends Fragment {
    FragmentRequestBinding binding;
    private View view;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding=FragmentRequestBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();

        binding.rlPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram_container,new ApplyPromoCodeFragment()).commit();
            }
        });


        binding.btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram_container,new ConfirmFrag()).commit();
            }
        });
        return view;
    }
}