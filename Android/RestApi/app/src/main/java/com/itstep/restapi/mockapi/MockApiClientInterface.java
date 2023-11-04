package com.itstep.restapi.mockapi;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MockApiClientInterface {
    @GET("https://65468155fe036a2fa955c7d7.mockapi.io/entity/") // Замените "your_endpoint" на конечную точку вашего API
    Call<List<EntityModel>> getAllEntities();
}
