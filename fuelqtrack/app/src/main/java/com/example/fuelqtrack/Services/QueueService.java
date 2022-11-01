package com.example.fuelqtrack.Services;

import com.example.fuelqtrack.Models.QueueModel;


import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QueueService {

    @GET("getPQueue/")
    Call<Object> getPQueue();

    @GET("getAQueue/{stationName}")
    Call<Object> getAQueue(@Path("stationName") String stationName);

    @GET("getCarQueue/{stationName}")
    Call<Object> getCarQueue(@Path("stationName") String stationName);

    @GET("getBikeQueue/{stationName}")
    Call<Object> getBikeQueue(@Path("stationName") String stationName);

    @GET("getLorryQueue/{stationName}")
    Call<Object> getLorryQueue(@Path("stationName") String stationName);

    @GET("getVanQueue/{stationName}")
    Call<Object> getVanQueue(@Path("stationName") String stationName);

    @POST("addQueue/")
    Call<Void> createQueue(@Body QueueModel queueModel);

    @PUT("{userMobile}")
    Call<QueueModel> updateQueue(@Path("userMobile") int userMobile, @Body QueueModel queueModel);

}
