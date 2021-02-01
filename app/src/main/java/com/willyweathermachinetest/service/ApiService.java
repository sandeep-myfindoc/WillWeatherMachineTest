package com.willyweathermachinetest.service;


import com.willyweathermachinetest.model.playerList.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {
    @GET("api/v1/players")
    Call<Response> getListOfPlayers(@Query("page") long page, @Query("per_page") int limit);
}