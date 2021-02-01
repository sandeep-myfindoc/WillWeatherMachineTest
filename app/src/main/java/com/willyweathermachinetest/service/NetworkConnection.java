package com.willyweathermachinetest.service;

import com.willyweathermachinetest.BuildConfig;

public class NetworkConnection {
    private String baseUrl;
    public NetworkConnection() {
        if(BuildConfig.DEBUG){
            this.baseUrl = BuildConfig.baseUrl;
        }else{
            this.baseUrl = BuildConfig.baseUrl;
        }
    }
    public String getBaseUrl() {
        return baseUrl;
    }
}
