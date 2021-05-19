package com.mycabuser.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mycabuser.Activity.NavigationActivity;
import com.mycabuser.MainActivity;
import com.mycabuser.R;
import com.mycabuser.databinding.FragmentConfirmBinding;

import static com.mycabuser.Utils.ProgressBarCustom.CustomDialog.dialog;


public class ConfirmFrag extends Fragment {
  FragmentConfirmBinding binding;
    private View view;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentConfirmBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog  dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_accept_layout);
                dialog.setCancelable(true);



                RelativeLayout rlDone= (RelativeLayout) dialog.findViewById(R.id.rlDone);
                RelativeLayout rlCancel= (RelativeLayout) dialog.findViewById(R.id.rlCancel);


                rlCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), NavigationActivity.class));
                        getActivity().finish();

                    }
                });

                dialog.show();
            }

        });
        return view;

    }
}