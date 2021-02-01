package com.willyweathermachinetest.util;

import android.content.Context;
import android.net.ConnectivityManager;
public class CommonUtility {
    private Context mContext;

    public CommonUtility(Context mContext) {
        this.mContext = mContext;
    }
    public boolean checkInternetConnection(){
        try {
            ConnectivityManager connectivityManager = ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE));
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        }catch (Exception ex){
            return false;
        }
    }
}
