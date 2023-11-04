package com.itstep.restapi.mockapi;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MockApiClient {
    private static final String BASE_URL = "https://65468155fe036a2fa955c7d7.mockapi.io/entity/"; // Замените на базовый URL вашего API

    private final MockApiClientInterface apiService;

    public MockApiClient() {
        // Создаем экземпляр Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Создаем экземпляр MockApiService
        apiService = retrofit.create(MockApiClientInterface.class);
    }

    public void getAllEntities(final ApiCallback<List<EntityModel>> callback) {
        Call<List<EntityModel>> call = apiService.getAllEntities();
        call.enqueue(new Callback<List<EntityModel>>() {
            @Override
            public void onResponse(Call<List<EntityModel>> call, Response<List<EntityModel>> response) {
                if (response.isSuccessful()) {
                    List<EntityModel> entities = response.body();
                    callback.onSuccess(entities);
                } else {
                    callback.onError("Ошибка при получении данных");
                }
            }

            @Override
            public void onFailure(Call<List<EntityModel>> call, Throwable t) {
                callback.onError("Ошибка при выполнении запроса");
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T data);

        void onError(String errorMessage);
    }
}