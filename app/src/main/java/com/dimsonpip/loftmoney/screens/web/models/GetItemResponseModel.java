package com.dimsonpip.loftmoney.screens.web.models;

import java.util.List;

public class GetItemResponseModel {

    private String status;
    private List<ItemRemote> data;

    public String getStatus() {
        return status;
    }

    public List<ItemRemote> getData() {
        return data;
    }
}
