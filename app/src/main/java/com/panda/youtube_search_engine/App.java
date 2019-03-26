package com.panda.youtube_search_engine;

import android.app.Application;
import timber.log.Timber;

public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
