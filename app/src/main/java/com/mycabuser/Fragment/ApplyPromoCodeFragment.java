package com.mycabuser.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mycabuser.APIData.API;
import com.mycabuser.APIData.JsonInterface;
import com.mycabuser.R;
import com.mycabuser.Utils.ProgressBarCustom.Appconstant;
import com.mycabuser.Utils.ProgressBarCustom.CustomDialog;
import com.mycabuser.Utils.ProgressBarCustom.SharedHelper;
import com.mycabuser.databinding.FragmentApplyPromoCodeBinding;
import com.mycabuser.retrofitmodel.ApplyPromoModel;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApplyPromoCodeFragment extends Fragment {
FragmentApplyPromoCodeBinding binding;
    private View view;
    private Context context;
    JsonInterface jsonInterface;
    Retrofit retrofit;
    String stCode="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentApplyPromoCodeBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();

        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stCode=binding.etPromo.getText().toString().trim();

                if (stCode.isEmpty()){
                    binding.etPromo.setError("Promo Code Must Required");
                }
                else {
                    applyPromoCode(stCode);
                }
            }
        });
        retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonInterface = retrofit.create(JsonInterface.class);
        return view;
    }

    private void applyPromoCode(String stCode){
        String    userId = SharedHelper.getKey(getActivity(), Appconstant.USERID);
        String    SELECTEDVEHICLEAMOUNT = SharedHelper.getKey(getActivity(), Appconstant.SELECTEDVEHICLEAMOUNT);

        Log.e("ApplyPromoCodeFragment", "userId: " +userId);
        Log.e("ApplyPromoCodeFragment", "SELECTEDVEHICLEAMOUNT: " +SELECTEDVEHICLEAMOUNT);
        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, getActivity());


        Map<String, String> param = new HashMap<>();
        param.put("promo_code", stCode);
        param.put("user_id", userId);
        param.put("total_amount", SELECTEDVEHICLEAMOUNT);


        Call<ApplyPromoModel> call = jsonInterface.applyPromo(param);

        call.enqueue(new Callback<ApplyPromoModel>() {
            @Override
            public void onResponse(Call<ApplyPromoModel> call, Response<ApplyPromoModel> response) {

                if (!response.isSuccessful()) {
                    dialog.hideDialog();
                    Toast.makeText(getActivity(), response.code() + " Failed", Toast.LENGTH_SHORT).show();

                }
                else {

                    ApplyPromoModel applyPromoModel = response.body();

                    Log.e("ApplyPromoCodeFragment", "onResponse: " +response.body());

                    if (applyPromoModel != null) {
                        dialog.hideDialog();

                        if (applyPromoModel.getResult().equals("Apply Successfully")){

                            Log.e("ApplyPromoCodeFragment", "onResponse: " +applyPromoModel.getResult());

                            SharedHelper.putKey(getActivity(), Appconstant.SELECTEDVEHICLE, applyPromoModel.getCodeAmount());

                        }

                    }

                }


            }

            @Override
            public void onFailure(Call<ApplyPromoModel> call, Throwable t) {
                dialog.hideDialog();
                Log.e("fdcvcxvbc ", "onFailure: " +t);
            }
        });

    }
}