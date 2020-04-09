package com.dimsonpip.loftmoney.screens.web;

import com.dimsonpip.loftmoney.screens.web.models.GetItemResponseModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetItemRequest {

    @GET("./items")
    Single<GetItemResponseModel> request(@Query("type") String string);
}
