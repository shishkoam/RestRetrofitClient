package com.yo.shishkoam.restclienttest.api;

import com.yo.shishkoam.restclienttest.api.models.TokenModel;
import com.yo.shishkoam.restclienttest.api.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by User on 21.02.2017
 */
public interface EasyPayApi {

    @FormUrlEncoded
    @POST("/api/token")
    Call<TokenModel> getToken(@Field("grant_type") String grant_type, @Field("username") String name, @Field("password") String password);

    @GET("/api/profile/current")
    Call<UserModel> getUser(@Header("Authorization") String token);
}
