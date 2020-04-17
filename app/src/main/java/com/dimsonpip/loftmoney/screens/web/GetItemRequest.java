package com.dimsonpip.loftmoney.screens.web;

import com.dimsonpip.loftmoney.screens.web.models.GetItemResponseModel;
import com.dimsonpip.loftmoney.screens.web.models.ItemRemote;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetItemRequest {

    @GET("./items")
    Single<List<ItemRemote>> request(@Query("type") String string,
                                     @Query("auth-token") String authToken);
}
