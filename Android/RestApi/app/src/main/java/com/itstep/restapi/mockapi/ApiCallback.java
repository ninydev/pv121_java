package com.itstep.restapi.mockapi;

/**
 * Шаблон ответа
 * - Что делать если данные получены
 * - Что делать, если произошла ошибка
 * @param <T>
 */
public interface ApiCallback<T> {
    void onSuccess(T data);

    void onError(String errorMessage);
}
