package com.willyweathermachinetest.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.willyweathermachinetest.model.playerList.Player;
import com.willyweathermachinetest.model.playerList.Response;
import com.willyweathermachinetest.service.ApiService;
import com.willyweathermachinetest.service.NetworkConnection;
import com.willyweathermachinetest.service.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
// It act as a Repositry
public class PlayerDataSource extends PageKeyedDataSource<Long, Player> {
    private ApiService service;
    private final int limit = 20;
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Player> callback) {
        Log.d("Intial Load","Intial Load");

        service = new RestClient(new NetworkConnection()).getClient();
        service.getListOfPlayers(1,limit).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response!=null && response.body()!=null) {
                    List<Player> playerList = response.body().getPlayersLst();
                    callback.onResult(playerList, null, (long) 2);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Player> callback) {
        Log.d("Load Before","Load Before");
        service = new RestClient(new NetworkConnection()).getClient();
        Log.d("Param key",params.key+"");
        service.getListOfPlayers(params.key,limit).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response!=null && response.body()!=null) {
                    List<Player> playerList = response.body().getPlayersLst();
                    long key;
                    if (params.key > 1) {
                        key = params.key - 1;
                    } else {
                        key = 0;
                    }
                    callback.onResult(playerList, key);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Player> callback) {
        Log.d("Load After","Load After");
        service = new RestClient(new NetworkConnection()).getClient();
        Log.d("Param key",params.key+"");
        service.getListOfPlayers(params.key,limit).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response!=null && response.body()!=null) {
                    List<Player> playerList = response.body().getPlayersLst();
                    callback.onResult(playerList, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}
