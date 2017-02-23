package com.yo.shishkoam.restclienttest.api;

import com.yo.shishkoam.restclienttest.api.models.HistoryModel;
import com.yo.shishkoam.restclienttest.api.models.TokenModel;
import com.yo.shishkoam.restclienttest.api.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by User on 21.02.2017
 */
public interface EasyPayApi {

    @FormUrlEncoded
    @POST("/api/token")
    Call<TokenModel> getToken(@Field("grant_type") String grant_type,
                              @Field("username") String name,
                              @Field("password") String password);

    @GET("/api/profile/current")
    Call<UserModel> getUser(@Header("Authorization") String token);

    @GET("/api/history")
    Call<HistoryModel> getHistory(@Header("Authorization") String token,
                                  @Query("model.dateStart") String dateStart,
                                  @Query("model.dateEnd") String dateEnd,
                                  @Query("model.pageNumber") int pageNumber,
                                  @Query("model.countPerPage") int countPerPage);
}
