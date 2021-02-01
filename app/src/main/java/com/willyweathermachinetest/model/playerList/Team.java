package com.willyweathermachinetest.model.playerList;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Team implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("abbreviation")
    private String abbreviation;
    @SerializedName("city")
    private String city;
    @SerializedName("conference")
    private String conference;
    @SerializedName("division")
    private String division;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("name")
    private String name;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAbbreviation() {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getConference() {
        return conference;
    }
    public void setConference(String conference) {
        this.conference = conference;
    }
    public String getDivision() {
        return division;
    }
    public void setDivision(String division) {
        this.division = division;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", abbreviation='" + abbreviation + '\'' +
                ", city='" + city + '\'' +
                ", conference='" + conference + '\'' +
                ", division='" + division + '\'' +
                ", fullName='" + fullName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
