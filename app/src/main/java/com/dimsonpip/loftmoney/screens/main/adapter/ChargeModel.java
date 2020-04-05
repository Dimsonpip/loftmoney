package com.dimsonpip.loftmoney.screens.main.adapter;

import android.view.View;

import com.dimsonpip.loftmoney.screens.web.models.ItemRemote;

import java.io.Serializable;

public class ChargeModel implements Serializable {
    public static String KEY_NAME = ChargeModel.class.getName();

    private String id;
    private String productName;
    private String productPrice;
    private int visibility;

    public ChargeModel(String productName, String productPrice) {
        this.id = "1";
        this.productName = productName;
        this.productPrice = productPrice;
        this.visibility = View.VISIBLE;
    }

    public ChargeModel(ItemRemote itemRemote) {
        this.id = new ItemRemote().getId();
        this.productName = new ItemRemote().getName();
        this.productPrice = new ItemRemote().getPrice() + " P";
        this.visibility = View.VISIBLE;

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
