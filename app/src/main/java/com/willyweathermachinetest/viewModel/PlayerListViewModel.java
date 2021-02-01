package com.willyweathermachinetest.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.willyweathermachinetest.model.playerList.Player;
import com.willyweathermachinetest.repositories.PlayerDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

//JsonObject
public class PlayerListViewModel extends AndroidViewModel {
    public LiveData<PagedList<Player>> playerPagedList;
    private Executor executor;
    public PlayerListViewModel(@NonNull Application application) {
        super(application);
        PlayerDataSourceFactory factory = new PlayerDataSourceFactory();
        //liveDataSource = factory.getMutableLiveData();
        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        executor = Executors.newFixedThreadPool(5);
        playerPagedList = (new LivePagedListBuilder<Long, Player>(factory, config))
                .setFetchExecutor(executor)
                .build();
    }
    public LiveData<PagedList<Player>> getPlayerPagedList() {
        return playerPagedList;
    }
}
