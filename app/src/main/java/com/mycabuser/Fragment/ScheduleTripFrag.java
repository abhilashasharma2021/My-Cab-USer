package com.mycabuser.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mycabuser.R;
import com.mycabuser.Utils.ProgressBarCustom.Appconstant;
import com.mycabuser.Utils.ProgressBarCustom.SharedHelper;
import com.mycabuser.databinding.FragmentScheduleTripBinding;

import java.util.Calendar;


public class ScheduleTripFrag extends Fragment {

  FragmentScheduleTripBinding binding;
  private Context context;
  private View view;

    String strDate="",stTime="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentScheduleTripBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();


        binding.rlDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String _year = String.valueOf(year);
                        String _month = (month + 1) < 10 ? "0" + (month + 1) : String.valueOf(month + 1);
                        String _date = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                        strDate = _date + "-" + month + "-" + _year;
                        Log.e("strpickedDate: ", "Date: " + _date + "-" + _month + "-" + _year); //2019-02-12
                        binding.txDate.setText(_date + "-" + _month + "-" + _year);
                        SharedHelper.putKey(getActivity(), Appconstant.SELECTEDATE, _date + "-" + _month + "-" + _year);

                    }
                },
                        c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.MONTH));
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.show();


            }
        });


        binding.rlTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String AM_PM;
                        if (selectedHour >=0 && selectedHour < 12){
                            AM_PM = "AM";
                        } else {
                            AM_PM = "PM";
                        }
                        stTime=selectedHour + ":" + selectedMinute+" "+AM_PM;
                        binding.txTime.setText( selectedHour + ":" + selectedMinute+" "+AM_PM);
                        SharedHelper.putKey(getActivity(), Appconstant.SELECTEDTIME, selectedHour + ":" + selectedMinute+" "+AM_PM);

                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strDate.equals("")){
                    Toast.makeText(context, "Select pick up date first", Toast.LENGTH_SHORT).show();
                }else if ( stTime.equals("")){
                    Toast.makeText(context, "Select pick up time  first", Toast.LENGTH_SHORT).show();
                }else {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram_container,new BottomPoularFragment()).commit();
                }







            }
        });


       return view;
    }
}