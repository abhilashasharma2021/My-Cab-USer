package com.mycabuser.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycabuser.R;
import com.mycabuser.databinding.FragmentApplyPromoCodeBinding;
import com.mycabuser.databinding.FragmentScheduleTripBinding;


public class ApplyPromoCodeFragment extends Fragment {
FragmentApplyPromoCodeBinding binding;
    private View view;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentApplyPromoCodeBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
        return view;
    }
}