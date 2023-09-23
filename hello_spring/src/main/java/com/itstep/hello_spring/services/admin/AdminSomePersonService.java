package com.itstep.hello_spring.services.admin;

import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import com.itstep.hello_spring.services.SomePersonService;
import com.itstep.hello_spring.viewModels.admin.SomePersonAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminSomePersonService {

    final
    SomePersonService personService;

    public AdminSomePersonService(SomePersonService personService) {
        this.personService = personService;
    }

    public SomePersonAdminResponse findAll() {
        return new SomePersonAdminResponse(
                true,
                personService.findAll()
        );
    }


}
