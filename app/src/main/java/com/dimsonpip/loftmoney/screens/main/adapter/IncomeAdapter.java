package com.dimsonpip.loftmoney.screens.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dimsonpip.loftmoney.R;

import java.util.ArrayList;
import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {

    private List<ChargeModel> mDataList = new ArrayList<>();

    public void setNewData(List<ChargeModel> newData) {
        mDataList.clear();
        mDataList.addAll(newData);
        notifyDataSetChanged();
    }

    public void addDataToTop(ChargeModel model) {
        mDataList.add(0, model);
        notifyItemInserted(0);
    }

    @NonNull
    @Override
    public IncomeAdapter.IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new IncomeAdapter.IncomeViewHolder(layoutInflater.inflate(R.layout.cell_income, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.IncomeViewHolder holder, int position) {
        holder.bind(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class IncomeViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName = itemView.findViewById(R.id.txtChargeName);
        private TextView txtPrice = itemView.findViewById(R.id.txtChargePrice);

        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(ChargeModel chargeModel) {
            txtName.setText(chargeModel.getProductName());
            txtPrice.setText(chargeModel.getProductPrice());

        }
    }
}
