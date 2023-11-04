package com.itstep.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.itstep.restapi.mockapi.ApiCallback;
import com.itstep.restapi.mockapi.EntityModel;
import com.itstep.restapi.mockapi.MockApiClient;
import com.itstep.restapi.mockapi.MockApiService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Обращение через еще один уровень абстракции (в нем красиво было бы спрятать адаптер)
        MockApiService apiService = new MockApiService();
        apiService.getAllEntities();


        // Обращение сразу к клиенту - в таком случае я получаю данные в активити
        MockApiClient apiClient = new MockApiClient();
        apiClient.getAllEntities(new ApiCallback<List<EntityModel>>() {
            @Override
            public void onSuccess(List<EntityModel> data) {
                for (EntityModel entity : data) {
                    Log.d("MainActivity:", entity.getName());
                }
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("MainActivity", errorMessage);
            }
        });

        EntityModel newEntity = new EntityModel();
        newEntity.setName("Вася Пупкин");
        apiClient.createEntity(newEntity, new ApiCallback<EntityModel>() {
            @Override
            public void onSuccess(EntityModel data) {
                Log.d("MainActivity:", data.getId() + " " + data.getName());
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("MainActivity", errorMessage);
            }
        });

        newEntity.setId("1");
        newEntity.setName("Петя Пушкин");

        apiClient.updateEntity(newEntity.getId(), newEntity, new ApiCallback<EntityModel>() {
            @Override
            public void onSuccess(EntityModel data) {
                Log.d("MainActivity Update", data.getId() + " " + data.getName());
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("MainActivity", errorMessage);
            }
        });

    }
}