package com.dimsonpip.loftmoney.screens.main.adapter;

import java.io.Serializable;

public class ChargeModel implements Serializable {
    public static String KEY_NAME = ChargeModel.class.getName();

    private String productName;
    private String productPrice;

    public ChargeModel(String productName, String productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }
}
