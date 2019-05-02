package com.gio.mscuentas;

import android.app.Application;

import com.gio.mscuentas.Utils.KeyStoreHelper;

public class msCuentas extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KeyStoreHelper.getInstance().init(this);
    }
}
