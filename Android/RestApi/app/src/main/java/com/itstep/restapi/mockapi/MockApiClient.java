package com.itstep.restapi.mockapi;


import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Выполняет запросы (является клиентом относительно Апи Сервера)
 */
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


    public void createEntity(EntityModel entity, final ApiCallback<EntityModel> callback) {
        Call<EntityModel> call = apiService.createEntity(entity);
        call.enqueue(new Callback<EntityModel>() {
            @Override
            public void onResponse(Call<EntityModel> call, Response<EntityModel> response) {
                if (response.isSuccessful()) {
                    EntityModel createdEntity = response.body();
                    callback.onSuccess(createdEntity);
                } else {
                    callback.onError("Ошибка при создании сущности");
                }
            }

            @Override
            public void onFailure(Call<EntityModel> call, Throwable t) {
                callback.onError("Ошибка при выполнении запроса");
            }
        });
    }

    public void updateEntity(String entityId, EntityModel updatedEntity, final ApiCallback<EntityModel> callback) {
        Call<EntityModel> call = apiService.updateEntity(entityId, updatedEntity);
        call.enqueue(new Callback<EntityModel>() {
            @Override
            public void onResponse(Call<EntityModel> call, Response<EntityModel> response) {
                if (response.isSuccessful()) {
                    EntityModel updatedEntity = response.body();
                    callback.onSuccess(updatedEntity);
                } else {
                    try {
                        Log.e("Update", response.errorBody().string().toString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    callback.onError("Ошибка при обновлении сущности");
                }
            }

            @Override
            public void onFailure(Call<EntityModel> call, Throwable t) {
                Log.e("Update", t.getMessage());
                callback.onError("Ошибка при выполнении запроса");
            }
        });
    }

}