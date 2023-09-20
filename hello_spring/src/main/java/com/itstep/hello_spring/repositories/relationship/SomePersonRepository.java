package com.itstep.hello_spring.repositories.relationship;

import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.UUID;

public interface SomePersonRepository extends JpaRepository<SomePerson, UUID>
{

    /**
     * Если мне нужно описать свой тип запроса к базе данных, я его описываю
     * в анатации. Например - моя версия выборки по id
     * @param id
     * @return
     */
    @Query("SELECT p FROM SomePerson p WHERE p.id=:id")
    SomePerson myFindById(UUID id);

    /**
     * Мой вариант запроса - для поиска по имени
     * @param name
     * @return
     */
    @Query("SELECT p FROM SomePerson p WHERE p.name=:name")
    ArrayList<SomePerson> myFindByName(String name);
}
