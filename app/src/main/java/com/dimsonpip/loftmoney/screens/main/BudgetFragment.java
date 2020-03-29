package com.dimsonpip.loftmoney.screens.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dimsonpip.loftmoney.R;
import com.dimsonpip.loftmoney.screens.main.adapter.ChargeModel;
import com.dimsonpip.loftmoney.screens.main.adapter.ChargesAdapter;
import com.dimsonpip.loftmoney.screens.second.AddItemActivity;

public class BudgetFragment extends Fragment {

    private ChargesAdapter chargesAdapter = new ChargesAdapter();
    static int ADD_ITEM_REQUEST = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_budget, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerMain);
        recyclerView.setAdapter(chargesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));


        view.findViewById(R.id.fabMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddItemActivityIntent = new Intent(getContext(), AddItemActivity.class);
                startActivityForResult(AddItemActivityIntent, ADD_ITEM_REQUEST);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ITEM_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            ChargeModel chargeModel = (ChargeModel) data.getSerializableExtra(ChargeModel.KEY_NAME);
            chargesAdapter.addDataToTop(chargeModel);
        }
    }
}
