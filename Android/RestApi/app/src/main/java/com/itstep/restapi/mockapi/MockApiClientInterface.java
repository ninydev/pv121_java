package com.itstep.restapi.mockapi;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;



public interface MockApiClientInterface {

    @POST("https://65468155fe036a2fa955c7d7.mockapi.io/entity/") // Замените на вашу конечную точку для создания сущности
    Call<EntityModel> createEntity(@Body EntityModel entity);


    @GET("https://65468155fe036a2fa955c7d7.mockapi.io/entity/") // Замените "your_endpoint" на конечную точку вашего API
    Call<List<EntityModel>> getAllEntities();



    @PUT("entity/{id}") // Замените "entity" на конечную точку вашего API для обновления сущности
    Call<EntityModel> updateEntity(@Path("id") String entityId, @Body EntityModel updatedEntity);

}
