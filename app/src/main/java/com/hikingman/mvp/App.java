package com.hikingman.mvp;

import android.app.Application;

import com.hikingman.mvp.x.MVP;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MVP.getInstance().install(this);
    }
}
