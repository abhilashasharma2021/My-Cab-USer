package com.mycabuser.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mycabuser.APIData.API;
import com.mycabuser.APIData.JsonInterface;
import com.mycabuser.R;
import com.mycabuser.Utils.ProgressBarCustom.Appconstant;
import com.mycabuser.Utils.ProgressBarCustom.CustomDialog;
import com.mycabuser.Utils.ProgressBarCustom.SharedHelper;
import com.mycabuser.adapter.JustGoAdapter;
import com.mycabuser.databinding.FragmentJustGoBinding;
import com.mycabuser.retrofitmodel.JustGoModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class JustGoFragment extends Fragment {
    private JustGoAdapter adapter;
    private ArrayList<JustGoModel.Datum> justList;
    FragmentJustGoBinding binding;
    private View view;
    private Context context;
    JsonInterface jsonInterface;
    Retrofit retrofit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentJustGoBinding.inflate(getLayoutInflater(), container, false);
        view = binding.getRoot();
        context = getActivity();

        retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        jsonInterface = retrofit.create(JsonInterface.class);




        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        binding.rvJustGo.setLayoutManager(mLayoutManager);


        getData();

        return view;


    }

    private void getData() {


    String    pickupLat = SharedHelper.getKey(getActivity(), Appconstant.PICK_USER_LAT);
    String    pickUpLong = SharedHelper.getKey(getActivity(), Appconstant.PICK_USER_LONG);
    String    dropOffLat = SharedHelper.getKey(getActivity(), Appconstant.DROPOFF_USER_LAT);
    String    dropOffLong = SharedHelper.getKey(getActivity(), Appconstant.DROPOFF_USER_LONG);

        Log.e("JustGoFragment", "getData: " +pickupLat);
        Log.e("JustGoFragment", "getData: " +pickUpLong);
        Log.e("JustGoFragment", "getData: " +dropOffLat);
        Log.e("JustGoFragment", "getData: " +dropOffLong);

        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, getActivity());


        Map<String, String> param = new HashMap<>();
         param.put("user_latitude1", pickupLat);
        param.put("user_longitude1", pickUpLong);
        param.put("drop_lat", dropOffLat);
        param.put("drop_long", dropOffLong);

        Call<JustGoModel> call = jsonInterface.nearme(param);
        call.enqueue(new Callback<JustGoModel>() {
            @Override
            public void onResponse(@NonNull Call<JustGoModel> call,@NonNull Response<JustGoModel> response) {
                if (!response.isSuccessful()) {
                    dialog.hideDialog();
                    Toast.makeText(getActivity(), response.code() + " Failed", Toast.LENGTH_SHORT).show();

                }else {
                    JustGoModel justGoModel = response.body();

                    Log.e("JustGoFragment", "onResponse: " +response.body());

                    if (justGoModel != null) {
                        dialog.hideDialog();
                        if (justGoModel.getStatus()) {
                            justList = new ArrayList<>();
                            List<JustGoModel.Datum> list = justGoModel.getData();
                            Log.e("JustGoFragment", "onResponse: " +justGoModel.getData());
                            justList.addAll(list);

                        }

                        else {


                            final Dialog dialog = new Dialog(getActivity());
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(true);
                            dialog.setContentView(R.layout.dialog_no_driver_available_layout);

                            Button btOk =(Button) dialog.findViewById(R.id.btOk);



                            btOk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Fragment fragment=new HomeFragment();
                                    FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.fram_container,fragment).commit();
                                    dialog.dismiss();
                                }
                            });

                            dialog.show();

                        }



                        }
                        adapter = new JustGoAdapter(context, justList);
                        binding.rvJustGo.setAdapter(adapter);
                    }


                }


            @Override
            public void onFailure(Call<JustGoModel> call, Throwable t) {
                dialog.hideDialog();
                Log.e("JustGoFragment", "onFailure: " +t);
            }
        });
    }
}