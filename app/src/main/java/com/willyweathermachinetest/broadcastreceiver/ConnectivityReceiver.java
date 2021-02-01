package com.willyweathermachinetest.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.willyweathermachinetest.util.CommonUtility;


public class ConnectivityReceiver extends BroadcastReceiver {
    public static ConnectivityReceiverListener connectivityReceiverListener;
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = new CommonUtility(context).checkInternetConnection();
        if(connectivityReceiverListener!=null)
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
    }
    public interface ConnectivityReceiverListener{
        void onNetworkConnectionChanged(boolean isConnected);
    }
}
