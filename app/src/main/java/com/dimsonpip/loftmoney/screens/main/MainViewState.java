package com.dimsonpip.loftmoney.screens.main;

import com.dimsonpip.loftmoney.screens.main.adapter.ChargeModel;

import java.util.List;

public interface MainViewState {

    void startLoading();
    void setData(List<ChargeModel> items);
    void setError(String error);
    void setNoItems();
}
