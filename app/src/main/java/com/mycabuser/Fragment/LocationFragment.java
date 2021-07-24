package com.mycabuser.Fragment;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.mycabuser.R;
import com.mycabuser.Utils.ProgressBarCustom.Appconstant;
import com.mycabuser.Utils.ProgressBarCustom.SharedHelper;
import com.mycabuser.databinding.FragmentLocationBinding;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class LocationFragment extends Fragment {
    private static final int AUTOCOMPLETE_REQUEST_CODE_SEARCHPICKUP= 1111;
    private static final int AUTOCOMPLETE_REQUEST_CODE_SEARCHDROP = 2222;
    FragmentLocationBinding binding;
    private View view;
    private Context context;
    String st_PickedLat="",st_PickedLong="",st_PickAddress="",st_DROPED_Lat="",st_DROPED_Long="",st_DROPEDAddress="",REQUESTSTATUSPAGE="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLocationBinding.inflate(getLayoutInflater(), container, false);
        view = binding.getRoot();
        context = getActivity();


        if (REQUESTSTATUSPAGE.equals("1")){

        }else {

        }


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (st_PickAddress.equals("")){
                    Toast.makeText(context, "Select pick up location first", Toast.LENGTH_SHORT).show();
                }else if (st_DROPEDAddress.equals("")){
                    Toast.makeText(context, "Select drop Off location first", Toast.LENGTH_SHORT).show();
                }else {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram_container, new ScheduleTripFrag()).commit();
                }

            }
        });

        if (!Places.isInitialized()) {
            Places.initialize(getActivity(), getString(R.string.api_key), Locale.getDefault());
        }


        binding.rlPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(getActivity());
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_SEARCHPICKUP);
            }
        });


        binding.rlDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*if app is crashed when type of place name it means billing is not enable of google api key */

                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(getActivity());
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_SEARCHDROP);
            }
        });

        return view;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE_SEARCHPICKUP) {
            if (resultCode == RESULT_OK) {

                if (data != null) {

                    Place place = Autocomplete.getPlaceFromIntent(data);
                    Log.e("oireuftoe", "Place: " + place.getName() + ", " + place.getLatLng() +
                            place.getAddress());

                    LatLng pickUplatlng = place.getLatLng();

                    try {
                        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

                        if (pickUplatlng != null) {

                            double st_PickLat = pickUplatlng.latitude;
                            double st_PickLong = pickUplatlng.longitude;
                            Log.e("Scflkl", st_PickLat + "");
                            Log.e("Scflkl", st_PickLong + "");

                            st_PickedLat = Double.toString(st_PickLat);
                            st_PickedLong = Double.toString(st_PickLong);

                            Log.e("sdkmcxkzkl", "Lat: " + st_PickedLat);
                            Log.e("sdkmcxkzkl", "Lng: " + st_PickedLong);


                            SharedHelper.putKey(getActivity(), Appconstant.PICK_USER_LAT, st_PickedLat);
                            SharedHelper.putKey(getActivity(), Appconstant.PICK_USER_LONG, st_PickedLong);

                            Log.e("rtrere", "latlng" + pickUplatlng.latitude + "," + pickUplatlng.longitude);
                            List<Address> addressList = geocoder.getFromLocation(pickUplatlng.latitude, pickUplatlng.longitude, 1);
                            SharedHelper.putKey(getActivity(), Appconstant.PICK_USER_Address, addressList.get(0).getAddressLine(0));
                            if (addressList != null) {

                                st_PickAddress = addressList.get(0).getAddressLine(0);
                                Log.e("rehhbfd", "msg : " + st_PickAddress);


                                if (st_PickAddress.equals("")) {
                                    binding.txtPickUp.setText("Select Pick Up Location");
                                } else {
                                    binding.txtPickUp.setText(st_PickAddress);


                                 //   show_chef();
                                }


                                //autoCompleteSearch.setText(address);

                            }
                        }


                    } catch (Exception e) {
                        Log.e("gfvdfrgvd", e.getMessage(), e);
                    }


                }


            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                assert status.getStatusMessage() != null;
                Log.e("oireuftoe", status.getStatusMessage());
            }

        }

        else if (requestCode==AUTOCOMPLETE_REQUEST_CODE_SEARCHDROP) {

            if (resultCode == RESULT_OK) {

                if (data != null) {

                    Place place = Autocomplete.getPlaceFromIntent(data);
                    Log.e("oireuftoe", "Place: " + place.getName() + ", " + place.getLatLng() +
                            place.getAddress());

                    LatLng dropUplatlng = place.getLatLng();

                    try {
                        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

                        if (dropUplatlng != null) {

                            double st_DropLat = dropUplatlng.latitude;
                            double st_DropLong = dropUplatlng.longitude;
                            Log.e("rgrehg", st_DropLat + "");
                            Log.e("rgrehg", st_DropLong + "");

                            st_DROPED_Lat = Double.toString(st_DropLat);
                            st_DROPED_Long = Double.toString(st_DropLong);

                            Log.e("hytjy", "Lat: " + st_DROPED_Lat);
                            Log.e("hytjy", "Lng: " + st_DROPED_Long);


                            SharedHelper.putKey(getActivity(), Appconstant.DROPOFF_USER_LAT, st_DROPED_Lat);
                            SharedHelper.putKey(getActivity(), Appconstant.DROPOFF_USER_LONG, st_DROPED_Long);

                            Log.e("grgbrtfbh", "latlng" + dropUplatlng.latitude + "," + dropUplatlng.longitude);
                            List<Address> addressList = geocoder.getFromLocation(dropUplatlng.latitude, dropUplatlng.longitude, 1);
                            SharedHelper.putKey(getActivity(), Appconstant.DROP_OFF_Address, addressList.get(0).getAddressLine(0));
                            if (addressList != null) {

                                st_DROPEDAddress = addressList.get(0).getAddressLine(0);
                                Log.e("grbhbfgbffb", "msg : " + st_DROPEDAddress);


                                if (st_DROPEDAddress.equals("")) {
                                    binding.txtDrop.setText("Select Drop Off Location");
                                } else {
                                    binding.txtDrop.setText(st_DROPEDAddress);

                                    //   show_chef();
                                }


                                //autoCompleteSearch.setText(address);

                            }
                        }


                    } catch (Exception e) {
                        Log.e("gfvdfrgvd", e.getMessage(), e);
                    }


                }


            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                assert status.getStatusMessage() != null;
                Log.e("oireuftoe", status.getStatusMessage());
            }

        }
    }
}
