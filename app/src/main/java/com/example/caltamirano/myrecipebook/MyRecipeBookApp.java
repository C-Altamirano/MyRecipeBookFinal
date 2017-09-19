package com.example.caltamirano.myrecipebook;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyRecipeBookApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Inicializar Fresco para toda la app
        Fresco.initialize(this);
    }
}
