package com.itstep.hello_spring.controllers.forServices;

import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import com.itstep.hello_spring.services.SomePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/persons")
public class ApiAdminPersonController {
    final
    SomePersonService personService;

    public ApiAdminPersonController(SomePersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public Iterable<SomePerson> getAllPersons(){
        return personService.findAll();
    }
}
