package com.itstep.verskta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected void log(String msg) {
        Log.d("MainActivity", msg);
    }

    protected void toast(String msg) {
        Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }


    public MainActivity(){
        super();
        log("Constructor");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        log("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this.getApplicationContext(), "THK U Return", Toast.LENGTH_LONG).show();
        log("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        log("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log("onDestroy");
    }
}