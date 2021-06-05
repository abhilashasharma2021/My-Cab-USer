package com.mycabuser.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.mycabuser.APIData.API;
import com.mycabuser.APIData.JsonInterface;
import com.mycabuser.R;
import com.mycabuser.Utils.ProgressBarCustom.Appconstant;
import com.mycabuser.Utils.ProgressBarCustom.CustomDialog;
import com.mycabuser.Utils.ProgressBarCustom.SharedHelper;
import com.mycabuser.databinding.FragmentRequestBinding;
import com.mycabuser.retrofitmodel.RequestModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RequestFragment extends Fragment {
    FragmentRequestBinding binding;
    private View view;
    private Context context;
    JsonInterface jsonInterface;
    Retrofit retrofit;
    String stPickLat = "", stPickLong = "", stDropLat = "", stDropLong = "", stSelectedDate = "", stSelectedTime = "", stDropAddress = "", stPickAddress = "", stSELECTEDVEHICLEId = "", stSelectedAmount = "", stUserId = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentRequestBinding.inflate(getLayoutInflater(), container, false);
        view = binding.getRoot();
        context = getActivity();
        String stSelectedAmount = SharedHelper.getKey(getActivity(), Appconstant.SELECTEDVEHICLEAMOUNT);
        String stSelectedDistance = SharedHelper.getKey(getActivity(), Appconstant.VEHICLEDISTANCE);

        Log.e("RequestFragment", "stSelectedDistance: " +stSelectedDistance);
        if (!stSelectedAmount.equals("")){
            binding.txtPrice.setText("$"+ stSelectedAmount);
        }else {

        }

        if (!stSelectedDistance.equals("")){
            binding.txtDistance.setText("$"+ stSelectedDistance);
        }else {

        }

        binding.rlPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram_container, new ApplyPromoCodeFragment()).commit();
            }
        });


        binding.btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_Request();
            }
        });


        retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonInterface = retrofit.create(JsonInterface.class);


        return view;


    }


    private void send_Request() {


        String stPickLat = SharedHelper.getKey(getActivity(), Appconstant.PICK_USER_LAT);
        String stPickLong = SharedHelper.getKey(getActivity(), Appconstant.PICK_USER_LONG);
        String stDropLat = SharedHelper.getKey(getActivity(), Appconstant.DROPOFF_USER_LAT);
        String stDropLong = SharedHelper.getKey(getActivity(), Appconstant.DROPOFF_USER_LONG);
        String stSelectedDate = SharedHelper.getKey(getActivity(), Appconstant.SELECTEDATE);
        String stSelectedTime = SharedHelper.getKey(getActivity(), Appconstant.SELECTEDTIME);
        String stDropAddress = SharedHelper.getKey(getActivity(), Appconstant.DROP_OFF_Address);
        String stPickAddress = SharedHelper.getKey(getActivity(), Appconstant.PICK_USER_Address);
        String stSELECTEDVEHICLEId = SharedHelper.getKey(getActivity(), Appconstant.SELECTEDVEHICLE);
        String stSelectedAmount = SharedHelper.getKey(getActivity(), Appconstant.SELECTEDVEHICLEAMOUNT);
        String stUserId = SharedHelper.getKey(getActivity(), Appconstant.USERID);


        Log.e("RequestFragment", "stPickLat: " + stPickLat);
        Log.e("RequestFragment", "stPickLong: " + stPickLong);
        Log.e("RequestFragment", "stPickAddress: " + stPickAddress);
        Log.e("RequestFragment", "stDropLat: " + stDropLat);
        Log.e("RequestFragment", "stDropLong: " + stDropLong);
        Log.e("RequestFragment", "stDropAddress: " + stDropAddress);
        Log.e("RequestFragment", "stSelectedTime: " + stSelectedTime);
        Log.e("RequestFragment", "stSelectedDate: " + stSelectedDate);
        Log.e("RequestFragment", "stSELECTEDVEHICLEId: " + stSELECTEDVEHICLEId);
        Log.e("RequestFragment", "stSelectedAmount: " + stSelectedAmount);
        Log.e("RequestFragment", "stUserId: " + stUserId);


        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, getActivity());

        Map<String, String> param = new HashMap<>();
        param.put("pickup_lat", stPickLat);
        param.put("pickup_long", stPickLong);
        param.put("drop_lat", stDropLat);
        param.put("drop_long", stDropLong);
        param.put("user_id", stUserId);
        param.put("amount", stSelectedAmount);
        param.put("vehicle_id", stSELECTEDVEHICLEId);
        param.put("pickup_location", stPickAddress);
        param.put("drop_location", stDropAddress);
        param.put("schedule_date", stSelectedDate);
        param.put("schedule_time", stSelectedDate);

        Call<RequestModel> call = jsonInterface.addRequest(param);
        call.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(@NonNull Call<RequestModel> call, @NonNull Response<RequestModel> response) {

                if (!response.isSuccessful()){
                    dialog.hideDialog();
                    Toast.makeText(getActivity(), response.code() + " Failed", Toast.LENGTH_SHORT).show();

                }else {
                    RequestModel requestModel=response.body();
                    Log.e("RequestFragment", "onResponse: " +response.body());

                    if (requestModel !=null){

                        dialog.hideDialog();

                        if (requestModel.getResult().equals("Successfully")){
                            Toast.makeText(getActivity(), requestModel.getResult(),Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram_container, new ConfirmFrag()).commit();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {
                dialog.hideDialog();

                Log.e("RequestFragment", "onFailure: " +t);
            }
        });


    }
}