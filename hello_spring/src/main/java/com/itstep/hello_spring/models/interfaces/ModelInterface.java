package com.itstep.hello_spring.models.interfaces;

import java.io.Serializable;

/**
 * Этот интерфейс относит все мои модели к единому типу
 */
public interface ModelInterface extends Serializable
{
    /**
     * Я описываю общее что есть у всех моделей в рамках задачи
     * @return
     */
    public String getName();
}
