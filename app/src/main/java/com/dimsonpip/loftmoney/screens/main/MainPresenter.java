package com.dimsonpip.loftmoney.screens.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.dimsonpip.loftmoney.R;
import com.dimsonpip.loftmoney.screens.main.adapter.ChargeModel;
import com.dimsonpip.loftmoney.screens.web.WebFactory;
import com.dimsonpip.loftmoney.screens.web.models.AuthResponse;
import com.dimsonpip.loftmoney.screens.web.models.ItemRemote;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private void loadItems() {
        SharedPreferences sharedPreferences = getContext().
                getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString(AuthResponse.AUTH_TOKEN_KEY, "");

        swipeRefreshLayout.setRefreshing(false);
        Disposable response = new WebFactory().getInstance().getItemRequest()
                .request("expense", authToken)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ItemRemote>>() {
                    @Override
                    public void accept(List<ItemRemote> itemRemotes) throws Exception {
                        List<ChargeModel> chargeModels = new ArrayList<>();
                        for (ItemRemote itemRemote: itemRemotes) {
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
