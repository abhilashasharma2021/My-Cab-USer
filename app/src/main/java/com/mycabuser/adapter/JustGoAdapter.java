package com.mycabuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycabuser.databinding.RowjustgolayoutBinding;
import com.mycabuser.databinding.RowpopularlayoutBinding;
import com.mycabuser.model.JustGoModel;
import com.mycabuser.model.PopularModel;

import java.util.ArrayList;

public class JustGoAdapter extends RecyclerView.Adapter<JustGoAdapter.MyViewHolder>{


    private Context mContext;
    private ArrayList<JustGoModel> justList;

    public JustGoAdapter(Context mContext, ArrayList<JustGoModel> justList) {
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
        JustGoModel modelObject = justList.get(position);
      holder.rowjustgolayoutBinding.txtName.setText(modelObject.getVehicalName());
      holder.rowjustgolayoutBinding.txtDistance.setText(modelObject.getDistance());
      holder.rowjustgolayoutBinding.txtPrice.setText(modelObject.getPrice());
      holder.rowjustgolayoutBinding.txtTime.setText(modelObject.getTime());
      holder.rowjustgolayoutBinding.ivlocation.setImageResource(modelObject.getVehicelImage());





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
