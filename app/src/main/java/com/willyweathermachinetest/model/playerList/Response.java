package com.willyweathermachinetest.model.playerList;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Response {
    @SerializedName("data")
    ArrayList<Player> playersLst;
    public ArrayList<Player> getPlayersLst() {
        return playersLst;
    }
    public void setPlayersLst(ArrayList<Player> playersLst) {
        this.playersLst = playersLst;
    }
}
