package com.mycabuser.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialogFragment;
import com.mycabuser.R;
import com.mycabuser.adapter.PopularAdapter;
import com.mycabuser.databinding.FragmentBottomPoularBinding;
import com.mycabuser.model.PopularModel;

import java.util.ArrayList;
import java.util.List;


public class BottomPoularFragment  extends RoundedBottomSheetDialogFragment {

    private PopularAdapter adapter;
    private ArrayList<PopularModel> popularList=new ArrayList<>();
    private FragmentBottomPoularBinding binding;
    private View view;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentBottomPoularBinding.inflate(getLayoutInflater(), container, false);
        view=binding.getRoot();
        context=getActivity();


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram_container,new JustGoFragment()).commit();
            }
        });

        adapter = new PopularAdapter(context, popularList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        binding.rvPopular.setLayoutManager(mLayoutManager);
        binding.rvPopular.setAdapter(adapter);

        getData();
        return  view;
    }
    private void getData(){
        PopularModel model=new PopularModel("University of Washington");
        for (int i = 0; i <4 ; i++) {
            popularList.add(model) ;
        }

    }
}