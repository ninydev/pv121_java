package com.itstep.restapi.mockapi;

import com.google.gson.annotations.SerializedName;

/**
 * Описание модели класса
 * Поля, типы полей, имена в JSON для сериализации и десериализации
 */
public class EntityModel {
    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    // Геттеры и сеттеры для полей (опционально)

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
