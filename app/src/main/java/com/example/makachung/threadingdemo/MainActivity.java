package com.example.makachung.threadingdemo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {

    private Button exceptionButton;
    private Button newThreadButton;
    private Button asyncButton;
    private Button asyncLoaderButton;

    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exceptionButton = findViewById(R.id.exception);
        exceptionButton.setOnClickListener(this);

        newThreadButton = findViewById(R.id.newThread);
        newThreadButton.setOnClickListener(this);

        asyncButton = findViewById(R.id.async);
        asyncButton.setOnClickListener(this);

        asyncLoaderButton = findViewById(R.id.asyncLoader);
        asyncLoaderButton.setOnClickListener(this);

        textView = findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exception:
                Utils.sendHttpRequest();
                break;
            case R.id.newThread:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //wrong approach
                        display(Utils.sendHttpRequest());
                    }
                }).start();
                break;
            case R.id.async:
                new NetworkRequestAsyncTask(this).execute();
                break;
            case R.id.asyncLoader:
                getSupportLoaderManager().restartLoader(1, null, this).forceLoad();
                break;
        }
    }



    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new NetworkRequestAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String string) {
        display(string);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        //Default ignore
    }

    private void display(String string) {
        textView.setText(string);
        DialogFragment fragment = new SImpleDialogFragment();
        fragment.show(getSupportFragmentManager(), "Dialog");
    }
}
