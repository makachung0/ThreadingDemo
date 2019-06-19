package com.example.makachung.threadingdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class NetworkRequestAsyncTaskLoader extends AsyncTaskLoader<String> {

    public NetworkRequestAsyncTaskLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return Utils.sendHttpRequest();
    }
}
