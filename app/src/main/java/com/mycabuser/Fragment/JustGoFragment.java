package com.mycabuser.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycabuser.R;
import com.mycabuser.adapter.JustGoAdapter;
import com.mycabuser.adapter.PopularAdapter;
import com.mycabuser.databinding.FragmentBottomPoularBinding;
import com.mycabuser.databinding.FragmentJustGoBinding;
import com.mycabuser.model.JustGoModel;
import com.mycabuser.model.PopularModel;

import java.util.ArrayList;


public class JustGoFragment extends Fragment {
    private JustGoAdapter adapter;
    private ArrayList<JustGoModel> justList=new ArrayList<>();
   FragmentJustGoBinding binding;
    private View view;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentJustGoBinding.inflate(getLayoutInflater(), container, false);
        view=binding.getRoot();
        context=getActivity();


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram_container,new RequestFragment()).commit();
            }
        });
        adapter = new JustGoAdapter(context, justList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        binding.rvJustGo.setLayoutManager(mLayoutManager);
        binding.rvJustGo.setAdapter(adapter);

        getData();

        return view;



    }

    private void getData(){
        JustGoModel model=new JustGoModel("Limousune"," $ 25.00","0.2 km"," 5min",R.drawable.car);
        for (int i = 0; i <4 ; i++) {
            justList.add(model) ;
        }

    }
}