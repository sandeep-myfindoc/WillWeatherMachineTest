package com.willyweathermachinetest.view.playerDetail;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.willyweathermachinetest.R;
import com.willyweathermachinetest.view.SuperActivity;
import com.willyweathermachinetest.databinding.ActivityPlayerDetailBinding;
import com.willyweathermachinetest.model.playerList.Player;
import com.willyweathermachinetest.sharedpreferences.SharedPreferencesName;

public class PlayerDetailActivity extends SuperActivity {
    private Player player;
    private ActivityPlayerDetailBinding binding;
    private String emptyLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_player_detail);
        updateToolbar(getResources().getString(R.string.title_playerDetail));
        emptyLabel= "__";
        if(getIntent()!=null){
            String playerString=getIntent().getStringExtra(SharedPreferencesName.PLAYER_DETAIL);
            player = new Gson().fromJson(playerString,Player.class);
            updateUI();
        }

    }

    private void updateUI() {
        if(player!=null) {
            binding.labelNameValue.setText(player.getFirstName() + " " + player.getLastName());
            binding.labelPositionValue.setText(player.getPosition() != null && !player.getPosition().equals("") ? player.getPosition() : emptyLabel);
            binding.labelheightValue.setText(((player.getHeightFeet() != null && !player.getHeightFeet().equals("") ? player.getHeightFeet() : emptyLabel )+ " Feet ") + ((player.getHeightInches() != null ? player.getHeightInches() : emptyLabel) + " Inches"));
            binding.labelweightValue.setText((player.getWeightPounds() != null ? player.getWeightPounds() : emptyLabel) + " Pounds");
            binding.labelTeamValue.setText(player.getTeam().getFullName() != null ? player.getTeam().getFullName() : emptyLabel);
            binding.labelCityValue.setText(player.getTeam().getCity() != null ? player.getTeam().getCity() : emptyLabel);
            binding.labelConfrenceValue.setText(player.getTeam().getConference() != null ? player.getTeam().getConference() : emptyLabel);
            binding.labelDivisionValue.setText(player.getTeam().getDivision() != null ? player.getTeam().getDivision() : emptyLabel);
        }
    }
}
