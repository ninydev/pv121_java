package com.itstep.hello_spring.controllers.relationship;

import com.itstep.hello_spring.models.relationship.one_many.SomeDocument;
import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import com.itstep.hello_spring.repositories.relationship.SomeDocumentRepository;
import com.itstep.hello_spring.repositories.relationship.SomePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/some_document")
public class SomeDocumentController {

    final
    SomeDocumentRepository documentRepository;
    SomePersonRepository personRepository;

    public SomeDocumentController(
            SomeDocumentRepository documentRepository,
            SomePersonRepository personRepository
    ) {
        this.documentRepository = documentRepository;
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public Iterable<SomeDocument> getAllPersons(){
        return documentRepository.findAll();
    }

    @GetMapping("/{id}")
    public SomeDocument getById(@PathVariable UUID id){
        SomeDocument document = documentRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        // Set<SomeDocument> l = person.getDocuments();
        return document;
    }

    @PostMapping("/")
    public ResponseEntity<SomeDocument> createDocument(@RequestBody SomeDocument newDocument) {

        // При создании связанных сущностей - следует проверять возможность создания (есть ли такой автор и тд)
        SomePerson person = personRepository.findById(newDocument.getPerson_id())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "person not found"));
        newDocument.setPerson(person);
        documentRepository.save(newDocument);

        return ResponseEntity
                .status(201)
                .body(newDocument);
    }
}
