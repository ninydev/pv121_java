package com.itstep.hello_spring.viewModels.admin;

import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Класс обертка - для формирования ответа для Админов
 * Такой сущности в базе нет - по этому мы описываем ее отдельно
 * Она будет работать только на отдачу данных для админов
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SomePersonAdminResponse implements Serializable
{
    private boolean success;
    private Iterable<SomePerson> data;
}
