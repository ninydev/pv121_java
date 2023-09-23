package com.itstep.hello_spring.services;

import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import com.itstep.hello_spring.repositories.relationship.SomePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SomePersonService {

    final
    SomePersonRepository personRepository;

    public SomePersonService(SomePersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public  Iterable<SomePerson> findAll(){
        return personRepository.findAll();
    }
}
