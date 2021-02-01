package com.willyweathermachinetest.repositories;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.willyweathermachinetest.model.playerList.Player;

public class PlayerDataSourceFactory extends DataSource.Factory<Long, Player> {
    MutableLiveData<PlayerDataSource> playerLiveDataSource;

    public PlayerDataSourceFactory() {
        this.playerLiveDataSource = new MutableLiveData<>();
    }

    @Override
    public DataSource<Long, Player> create() {
        PlayerDataSource playerDataSource = new PlayerDataSource();
        playerLiveDataSource.postValue(playerDataSource);
        return playerDataSource;
    }

    public MutableLiveData<PlayerDataSource> getMutableLiveData() {
        return playerLiveDataSource;
    }
}
