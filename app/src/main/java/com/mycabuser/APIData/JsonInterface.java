package com.mycabuser.APIData;

import com.mycabuser.retrofitmodel.ApplyPromoModel;
import com.mycabuser.retrofitmodel.CancelRideResponce;
import com.mycabuser.retrofitmodel.DriverDetailResponce;
import com.mycabuser.retrofitmodel.JustGoModel;
import com.mycabuser.retrofitmodel.RequestModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JsonInterface {


    @FormUrlEncoded
    @POST(API.showVehicle)
    Call<JustGoModel> nearme(@FieldMap Map<String,String> params);


    @FormUrlEncoded
    @POST(API.ApplyPromo)
    Call<ApplyPromoModel> applyPromo(@FieldMap Map<String,String> params);


    @FormUrlEncoded
    @POST(API.AddRequestBooking)
    Call<RequestModel>addRequest(@FieldMap Map<String ,String>params);

    @FormUrlEncoded
    @POST(API.ShowDriverResponce)
    Call<DriverDetailResponce>showDriverDetails(@FieldMap Map<String ,String>params);

    @FormUrlEncoded
    @POST(API.UserCancelRide)
    Call<CancelRideResponce>cancelRide(@FieldMap Map<String ,String>params);

}
