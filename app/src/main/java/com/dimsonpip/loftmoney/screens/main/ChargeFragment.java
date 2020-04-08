package com.dimsonpip.loftmoney.screens.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dimsonpip.loftmoney.R;
import com.dimsonpip.loftmoney.screens.main.adapter.ChargeModel;
import com.dimsonpip.loftmoney.screens.main.adapter.ChargesAdapter;
import com.dimsonpip.loftmoney.screens.second.AddItemActivity;
import com.dimsonpip.loftmoney.screens.web.WebFactory;
import com.dimsonpip.loftmoney.screens.web.models.GetItemResponseModel;
import com.dimsonpip.loftmoney.screens.web.models.ItemRemote;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ChargeFragment extends Fragment {

    private List<Disposable> disposables = new ArrayList<>();
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
    public void onStart() {
        super.onStart();
        loadItems();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        for(Disposable disposable: disposables) {
            disposable.dispose();
        }
        super.onStop();
    }

    private void loadItems() {
        Disposable response = new WebFactory().getInstance().getItemRequest().request("expense")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetItemResponseModel>() {
                    @Override
                    public void accept(GetItemResponseModel getItemResponseModel) throws Exception {
                        List<ChargeModel> chargeModels = new ArrayList<>();
                        for (ItemRemote itemRemote: getItemResponseModel.getData()) {
                            chargeModels.add(new ChargeModel(itemRemote));
                        }

                        chargesAdapter.setNewData(chargeModels);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        disposables.add(response);

    }
}
