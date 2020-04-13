package com.dimsonpip.loftmoney.screens.main;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dimsonpip.loftmoney.R;
import com.dimsonpip.loftmoney.screens.main.adapter.ChargeModel;
import com.dimsonpip.loftmoney.screens.main.adapter.IncomeAdapter;
import com.dimsonpip.loftmoney.screens.web.WebFactory;
import com.dimsonpip.loftmoney.screens.web.models.GetItemResponseModel;
import com.dimsonpip.loftmoney.screens.web.models.ItemRemote;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class IncomeFragment extends Fragment {

    private IncomeAdapter incomeAdapter = new IncomeAdapter();
    private List<Disposable> disposables = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_budget, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerMain);
        recyclerView.setAdapter(incomeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccentMain);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadItems();
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
        swipeRefreshLayout.setRefreshing(false);
        Disposable response = new WebFactory().getInstance().getItemRequest().request("income")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetItemResponseModel>() {
                    @Override
                    public void accept(GetItemResponseModel getItemResponseModel) throws Exception {
                        List<ChargeModel> chargeModels = new ArrayList<>();
                        for (ItemRemote itemRemote: getItemResponseModel.getData()) {
                            chargeModels.add(new ChargeModel(itemRemote));
                        }

                        incomeAdapter.setNewData(chargeModels);
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

