package com.willyweathermachinetest.view;
import android.app.Application;
import android.content.Context;
import com.willyweathermachinetest.broadcastreceiver.ConnectivityReceiver;

public class WillyWeatherApplication extends Application {
    private static WillyWeatherApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public static synchronized WillyWeatherApplication getInstance() {
        return mInstance;
    }
    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
