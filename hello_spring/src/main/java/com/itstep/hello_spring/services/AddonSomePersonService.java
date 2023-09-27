package com.itstep.hello_spring.services;

import com.itstep.hello_spring.repositories.relationship.SomePersonRepository;
import com.itstep.hello_spring.services.interfaces.AddonSomePersonServiceInterface;

public class AddonSomePersonService
        extends SomePersonService
        implements AddonSomePersonServiceInterface
{
    public AddonSomePersonService(SomePersonRepository personRepository) {
        super(personRepository);
    }


    @Override
    public void addSomeFunction() {

    }
}
