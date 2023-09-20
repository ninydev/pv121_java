package com.itstep.hello_spring.controllers.relationship;

import com.itstep.hello_spring.models.relationship.one_many.SomeDocument;
import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import com.itstep.hello_spring.repositories.relationship.SomePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.*;

@RestController
@RequestMapping("/api/some_person")
public class SomePersonController {

    private final SomePersonRepository somePersonRepository;

    public SomePersonController(SomePersonRepository somePersonRepository) {
        this.somePersonRepository = somePersonRepository;
    }

    @GetMapping("/")
    public Iterable<SomePerson> getAllPersons(){
        return somePersonRepository.findAll();
    }

    @GetMapping("search_by_name/{name}")
    public ResponseEntity<ArrayList<SomePerson>> searchByNameByStream(@PathVariable String name) {

        ArrayList<SomePerson> find = somePersonRepository.myFindByName(name);

        //*---------------------
        // Этот медод плох, поскольку выборка (сорировка манипуляция с коллекцией) происходит уже с
        // выгруженными данными

//        // SELECT * FROM `some_persons`
//        ArrayList<SomePerson> all = (ArrayList<SomePerson>) somePersonRepository.findAll();
//        // А я хочу: SELECT * FROM `some_persons` WHERE some_persons.name=name
//
//        // Эта операция происходит уже в оперативной памяти
//        ArrayList<SomePerson> find = (ArrayList<SomePerson>) all.stream().filter(p -> p.getName().equals(name));


        return ResponseEntity
                .status(200)
                .body(find);
    }

    @GetMapping("/{id}")
    public SomePerson getById(@PathVariable UUID id){
        SomePerson person = somePersonRepository.myFindById(id);
//        SomePerson person = somePersonRepository.findById(id)
//                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        return person;
    }
}
