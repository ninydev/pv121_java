package com.itstep.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itstep.restapi.mockapi.MockApiService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MockApiService api = new MockApiService();
        api.getAllEntities();
    }
}