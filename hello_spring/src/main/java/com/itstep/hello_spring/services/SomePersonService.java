package com.itstep.hello_spring.services;

import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import com.itstep.hello_spring.repositories.relationship.SomePersonRepository;
import com.itstep.hello_spring.services.interfaces.SomePersonServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class SomePersonService implements SomePersonServiceInterface
{

    final
    SomePersonRepository personRepository;

    public SomePersonService(SomePersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Cacheable(value = "persons", key = "'allPersons'", unless = "#result == null")
    public  Iterable<SomePerson> findAll(){
        return personRepository.findAll();
    }
}
