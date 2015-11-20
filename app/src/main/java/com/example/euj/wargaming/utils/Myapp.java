package com.example.euj.wargaming.utils;

import android.app.Application;

/**
 * Created by Euj on 09/11/2015.
 */
public class Myapp extends Application {
    public boolean Connected=false;
    public boolean isConnected()
    {
        return Connected;
    }
    public void setConnected(boolean connected)
    {
        Connected=connected;
    }

}

