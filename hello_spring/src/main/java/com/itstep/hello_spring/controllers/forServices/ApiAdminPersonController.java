package com.itstep.hello_spring.controllers.forServices;

import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import com.itstep.hello_spring.services.SomePersonService;
import com.itstep.hello_spring.services.admin.AdminSomePersonService;
import com.itstep.hello_spring.services.helpers.email.EmailService;
import com.itstep.hello_spring.viewModels.admin.SomePersonAdminResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/persons")
public class ApiAdminPersonController {
    final
    SomePersonService personService;
    final
    AdminSomePersonService adminSomePersonService;
    final
    EmailService emailService;

    public ApiAdminPersonController(
            SomePersonService personService,
            AdminSomePersonService adminSomePersonService,
            EmailService emailService
    ) {
        this.personService = personService;
        this.adminSomePersonService = adminSomePersonService;
        this.emailService = emailService;
    }

    @GetMapping("")
    public Iterable<SomePerson> getAllPersons(){
        emailService.sendEmail("keeper@ninydev.com", "Test", "Body");
        return personService.findAll();
    }

    @GetMapping("/ad")
    public SomePersonAdminResponse getByRes() {
        return adminSomePersonService.findAll();
    }
}
