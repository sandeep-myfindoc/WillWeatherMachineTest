package com.willyweathermachinetest.model.playerList;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Player implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("height_feet")
    private String heightFeet;
    @SerializedName("height_inches")
    private String heightInches;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("position")
    private String position;
    @SerializedName("team")
    private Team team;
    @SerializedName("weight_pounds")
    private String weightPounds;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getHeightFeet() {
        return heightFeet;
    }
    public void setHeightFeet(String heightFeet) {
        this.heightFeet = heightFeet;
    }
    public String getHeightInches() {
        return heightInches;
    }
    public void setHeightInches(String heightInches) {
        this.heightInches = heightInches;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    public String getWeightPounds() {
        return weightPounds;
    }
    public void setWeightPounds(String weightPounds) {
        this.weightPounds = weightPounds;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", heightFeet='" + heightFeet + '\'' +
                ", heightInches='" + heightInches + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", team=" + team +
                ", weightPounds='" + weightPounds + '\'' +
                '}';
    }
}
