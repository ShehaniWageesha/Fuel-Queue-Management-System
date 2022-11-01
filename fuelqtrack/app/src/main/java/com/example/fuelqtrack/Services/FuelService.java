package com.example.fuelqtrack.Services;

import com.example.fuelqtrack.Models.StationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FuelService {
    @GET("getAllStations/")
    Call<List<StationModel>> getAllStations();

    @POST("add/")
    Call<StationModel> insertStation(@Body StationModel stationModel);

    @PUT("{id}")
    Call<Void> updateStation(@Path("id") String _id, @Body StationModel stationModel);
}

