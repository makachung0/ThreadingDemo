package com.example.makachung.threadingdemo;

import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

public class NetworkRequestAsyncTask extends AsyncTask<Void, Void, String> {

    private MainActivity activity;

    public NetworkRequestAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Void[] objects) {
        return Utils.sendHttpRequest();
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        //not so good approach again
        activity.textView.setText(string);

        DialogFragment fragment = new SImpleDialogFragment();
        fragment.show(activity.getSupportFragmentManager(), "dialog");

    }
}
