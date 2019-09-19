package com.example.retrofit.App;

import android.app.Application;


public class App extends Application {

    private static App instance;

    public App() {

    }

    public static App getInstance() {
        if (instance == null)
            instance = new App();
        return instance;
    }
}
