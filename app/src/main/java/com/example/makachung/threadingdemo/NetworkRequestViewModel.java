package com.example.makachung.threadingdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class NetworkRequestViewModel extends ViewModel {

    public MutableLiveData<String> responseCode = new MutableLiveData<>();

    public LiveData<String> get() {
        load();
        return responseCode;
    }
    private void load() {
        responseCode.postValue(Utils.sendHttpRequest());
    }
}
