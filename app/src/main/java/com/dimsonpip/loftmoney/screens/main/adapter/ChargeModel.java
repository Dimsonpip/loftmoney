package com.dimsonpip.loftmoney.screens.main.adapter;

import com.dimsonpip.loftmoney.screens.web.models.ItemRemote;

import java.io.Serializable;

public class ChargeModel implements Serializable {
    public static String KEY_NAME = ChargeModel.class.getName();

    private String id;
    private String productName;
    private String productPrice;

    public ChargeModel(ItemRemote itemRemote) {
        this.id = itemRemote.getId();
        this.productName = itemRemote.getName();
        this.productPrice = itemRemote.getPrice() + " ₽";
    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }
}
