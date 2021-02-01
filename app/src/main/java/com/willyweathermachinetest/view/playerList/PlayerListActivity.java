package com.willyweathermachinetest.view.playerList;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.willyweathermachinetest.R;
import com.willyweathermachinetest.util.CommonUtility;
import com.willyweathermachinetest.view.SuperActivity;
import com.willyweathermachinetest.broadcastreceiver.ConnectivityReceiver;
import com.willyweathermachinetest.databinding.ActivityMainBinding;
import com.willyweathermachinetest.model.playerList.Player;
import com.willyweathermachinetest.view.WillyWeatherApplication;
import com.willyweathermachinetest.viewModel.PlayerListViewModel;

public class PlayerListActivity extends SuperActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    private ActivityMainBinding binding;
    private PlayerListViewModel viewModel;
    private PlayerListAdapter adapter;
    private ConnectivityReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(PlayerListViewModel.class);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
        receiver = new ConnectivityReceiver();
        WillyWeatherApplication.getInstance().setConnectivityListener(this);
        updateToolbarWithoutBackButton(getResources().getString(R.string.title_playerList));
        initRecyclerView();

        binding.swipeLayout.setOnRefreshListener(onRefreshListener);
        if (!new CommonUtility(this).checkInternetConnection()) {
            Snackbar snackbar = Snackbar
                    .make(binding.coordinatorLayout, getResources().getString(R.string.msg_offline), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        binding.swipeLayout.setRefreshing(true);
        fetchPlayersList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    private void initRecyclerView() {
        adapter = new PlayerListAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvPlayers.setLayoutManager(layoutManager);
        binding.rvPlayers.setAdapter(adapter);
    }

    //add observer
    private void fetchPlayersList() {
        viewModel.getPlayerPagedList().observe(this, new Observer<PagedList<Player>>() {
            @Override
            public void onChanged(PagedList<Player> players) {
                binding.swipeLayout.setRefreshing(false);
                adapter.submitList(players);
                if (binding.swipeLayout.isRefreshing()) {
                    binding.swipeLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            binding.swipeLayout.setRefreshing(true);
            fetchPlayersList();
        } else {
            Snackbar snackbar = Snackbar
                    .make(binding.coordinatorLayout, getResources().getString(R.string.msg_offline), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            binding.swipeLayout.setRefreshing(true);
            fetchPlayersList();
        }
    };
}
