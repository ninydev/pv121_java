package com.itstep.restapi.mockapi;

import android.util.Log;

import java.util.List;

public class MockApiService {

    private final MockApiClient mockApiClient;

    public MockApiService() {
        mockApiClient = new MockApiClient();
    }

    public void getAllEntities () {


        // Вызываем метод для получения данных
        mockApiClient.getAllEntities(new MockApiClient.ApiCallback<List<EntityModel>>() {
            @Override
            public void onSuccess(List<EntityModel> data) {
                // Обработка успешного получения данных
                for (EntityModel entity : data) {
                    Log.d("MockApiEntity:", entity.toString());
                }
            }

            @Override
            public void onError(String errorMessage) {
                // Обработка ошибки при получении данных
                Log.e("MockApiService", errorMessage);
            }
        });
    }
}
