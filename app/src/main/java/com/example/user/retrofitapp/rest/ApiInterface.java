package com.example.user.retrofitapp.rest;

import com.example.user.retrofitapp.model.Items;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("jakarta/{periode}/dayli.json")
    Call<Items> getJadwalSholat(@Path("periode") String periode);

}
