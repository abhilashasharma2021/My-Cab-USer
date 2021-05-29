package com.mycabuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mycabuser.Fragment.ConfirmFrag;
import com.mycabuser.Fragment.RequestFragment;
import com.mycabuser.R;
import com.mycabuser.Utils.ProgressBarCustom.Appconstant;
import com.mycabuser.Utils.ProgressBarCustom.SharedHelper;
import com.mycabuser.databinding.RowjustgolayoutBinding;
import com.mycabuser.retrofitmodel.JustGoModel;

import java.util.ArrayList;

public class JustGoAdapter extends RecyclerView.Adapter<JustGoAdapter.MyViewHolder>{


    private final Context mContext;
    private final ArrayList<JustGoModel.Datum> justList;

    public JustGoAdapter(Context mContext, ArrayList<JustGoModel.Datum> justList) {
        this.mContext = mContext;
        this.justList = justList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowjustgolayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        JustGoModel.Datum modelObject = justList.get(position);

        if (modelObject!=null){
            holder.rowjustgolayoutBinding.txtName.setText(modelObject.getTexi().getName());
           holder.rowjustgolayoutBinding.txtTime.setText(modelObject.getTime());
            holder.rowjustgolayoutBinding.txtPrice.setText(modelObject.getTotalAmount());
            holder.rowjustgolayoutBinding.txtDistance.setText(modelObject.getTotalDistance());


            try {
                Glide.with(mContext).load(modelObject.getTexi().getPath() + modelObject.getTexi().getImage())
                        .placeholder(R.drawable.dummy).override(250, 250)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.rowjustgolayoutBinding.ivlocation);
            } catch (Exception e) {

            }

            holder.rowjustgolayoutBinding.rlConatainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.rowjustgolayoutBinding.rlConatainer.setBackgroundColor(mContext.getResources().getColor(R.color.purple_200));
                    SharedHelper.putKey(mContext, Appconstant.SELECTEDVEHICLE, modelObject.getId());
                    SharedHelper.putKey(mContext, Appconstant.VEHICLEDISTANCE, modelObject.getDistance());
                    SharedHelper.putKey(mContext, Appconstant.SELECTEDVEHICLEAMOUNT, modelObject.getTotalAmount());

                    Fragment fragment=new RequestFragment();
                    FragmentManager fragmentManager=((FragmentActivity) mContext).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fram_container,fragment).commit();



                }
            });


        }








    }

    @Override
    public int getItemCount() {
        return justList == null ? 0 : justList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowjustgolayoutBinding rowjustgolayoutBinding;
        public MyViewHolder(RowjustgolayoutBinding rowjustgolayoutBinding) {
            super(rowjustgolayoutBinding.getRoot());
            this.rowjustgolayoutBinding = rowjustgolayoutBinding;
        }

    }
}
