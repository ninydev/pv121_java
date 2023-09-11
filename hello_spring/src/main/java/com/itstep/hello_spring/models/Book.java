package com.itstep.hello_spring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Описание модели - в основе содержит настройку сущности
 * и ее положение в таблице в базе данных
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book
{
    // Применение ID по принципу автонаполнения
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    // Реализация генерации UUID
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Поле varChar255 - формирование колонки в таблице
    private String title;
}
