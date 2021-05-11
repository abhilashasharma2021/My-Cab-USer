package com.mycabuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.mycabuser.databinding.RowpopularlayoutBinding;
import com.mycabuser.model.PopularModel;

import java.util.ArrayList;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MyViewHolder>{


    private Context mContext;
    private ArrayList<PopularModel> popularList;

    public PopularAdapter(Context mContext, ArrayList<PopularModel> popularList) {
        this.mContext = mContext;
        this.popularList = popularList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowpopularlayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PopularModel modelObject = popularList.get(position);
      holder.rowpopularlayoutBinding.txtName.setText(modelObject.getPlaceName());





    }

    @Override
    public int getItemCount() {
        return popularList == null ? 0 : popularList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowpopularlayoutBinding rowpopularlayoutBinding;
        public MyViewHolder(RowpopularlayoutBinding rowpopularlayoutBinding) {
            super(rowpopularlayoutBinding.getRoot());
            this.rowpopularlayoutBinding = rowpopularlayoutBinding;
        }

    }
}
