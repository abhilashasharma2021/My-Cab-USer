package com.mycabuser.Fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mycabuser.APIData.API;
import com.mycabuser.APIData.JsonInterface;
import com.mycabuser.Activity.NavigationActivity;
import com.mycabuser.MainActivity;
import com.mycabuser.R;
import com.mycabuser.Utils.ProgressBarCustom.Api;
import com.mycabuser.Utils.ProgressBarCustom.Appconstant;
import com.mycabuser.Utils.ProgressBarCustom.CustomDialog;
import com.mycabuser.Utils.ProgressBarCustom.SharedHelper;
import com.mycabuser.databinding.FragmentConfirmBinding;
import com.mycabuser.retrofitmodel.CancelRideResponce;
import com.mycabuser.retrofitmodel.DriverDetailResponce;
import com.mycabuser.retrofitmodel.RequestModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mycabuser.Utils.ProgressBarCustom.CustomDialog.dialog;


public class ConfirmFrag extends Fragment {
    FragmentConfirmBinding binding;
    private View view;
    private Context context;
    JsonInterface jsonInterface;
    Retrofit retrofit;
    String REGID = "";
    String requestId="",REQUESTSTATUSPAGE="";
    BroadcastReceiver mRegistrationBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("dfgfdgfdg", "onReceive: ");


            String result = intent.getStringExtra("title");
            requestId = intent.getStringExtra("id");


            Log.e("tgyhtghyyt", result);
            Log.e("tgyhtghyyt", requestId);

            if (result.equals("Your booking is confirm driver will pick  you soon")) {
                // showDriverDetails(requestId);
            }


        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentConfirmBinding.inflate(getLayoutInflater(), container, false);
        view = binding.getRoot();
        context = getActivity();
        requestId = SharedHelper.getKey(getActivity(), Appconstant.REQUESTID);
        REQUESTSTATUSPAGE = SharedHelper.getKey(getActivity(), Appconstant.REQUESTSTATUSPAGE);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                REGID = task.getResult();
                Log.e("saaddasadsdas", REGID + "dhfd");
                SharedHelper.putKey(getActivity(), Appconstant.REG_ID_TOKEN, REGID);
            }
        });


        retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonInterface = retrofit.create(JsonInterface.class);


        binding.btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_CancelRequest(requestId);
            }
        });

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_accept_layout);
                dialog.setCancelable(true);


                RelativeLayout rlDone = (RelativeLayout) dialog.findViewById(R.id.rlDone);
                RelativeLayout rlCancel = (RelativeLayout) dialog.findViewById(R.id.rlCancel);


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

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter("Check"));
    }

    private void showDriverDetails(String requestId) {

        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, getActivity());

        Map<String, String> param = new HashMap<>();
        param.put("id", requestId);

        Call<DriverDetailResponce> call = jsonInterface.showDriverDetails(param);

        call.enqueue(new Callback<DriverDetailResponce>() {
            @Override
            public void onResponse(Call<DriverDetailResponce> call, Response<DriverDetailResponce> response) {

                if (!response.isSuccessful()){
                    dialog.hideDialog();
                    Toast.makeText(getActivity(), response.code() + " Failed", Toast.LENGTH_SHORT).show();

                }else {
                    DriverDetailResponce model=response.body();
                    Log.e("vxcvcxv", "onResponse: " +response.body());

                    if (model !=null){

                        dialog.hideDialog();

                        if (model.getResult().equals("Successfully")){

                            binding.txtDriverName.setText(model.getUserData().getName());
                            Toast.makeText(getActivity(), model.getResult(),Toast.LENGTH_SHORT).show();
                          //  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram_container, new ConfirmFrag()).commit();
                        }
                    }
                }


            }

            @Override
            public void onFailure(Call<DriverDetailResponce> call, Throwable t) {
                dialog.hideDialog();
            }
        });


    }



    /* 0=user_book,1=driver_confirm 2=driver_cancle 3=user_cancle 4=userc_confirm*/



    private void user_CancelRequest(String requestId){

        CustomDialog dialog = new CustomDialog();
        dialog.showDialog(R.layout.progress_layout, getActivity());

        Map<String, String> param = new HashMap<>();
        param.put("booking_id", requestId);
        param.put("cnf_status", "3");

        Call<CancelRideResponce> call = jsonInterface.cancelRide(param);

        call.enqueue(new Callback<CancelRideResponce>() {
            @Override
            public void onResponse(Call<CancelRideResponce> call, Response<CancelRideResponce> response) {

                if (!response.isSuccessful()){
                    dialog.hideDialog();
                    Toast.makeText(getActivity(), response.code() + " Failed", Toast.LENGTH_SHORT).show();

                }else {
                    CancelRideResponce model=response.body();
                    Log.e("vxcvcxv", "onResponse: " +response.body().getResult());

                    if (model !=null){

                        dialog.hideDialog();

                        if (model.getResult().equals("successfully")){
                            Toast.makeText(getActivity(), model.getResult(),Toast.LENGTH_SHORT).show();
                            SharedHelper.putKey(getActivity(), Appconstant.REQUESTSTATUSPAGE,"");
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                        }
                    }
                }


            }

            @Override
            public void onFailure(Call<CancelRideResponce> call, Throwable t) {
                dialog.hideDialog();
            }
        });


    }

}
