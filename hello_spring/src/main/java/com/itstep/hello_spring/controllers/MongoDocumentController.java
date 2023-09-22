package com.itstep.hello_spring.controllers;

import com.itstep.hello_spring.models.mongo.MongoDocuments;
import com.itstep.hello_spring.repositories.MongoDocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/mongo")
public class MongoDocumentController {

    final
    MongoDocumentsRepository documentsRepository;

    public MongoDocumentController(MongoDocumentsRepository documentsRepository) {
        this.documentsRepository = documentsRepository;
    }

    @GetMapping("")
    public Iterable<MongoDocuments> getAll() {
        return documentsRepository.findAll();
    }

    @PostMapping("")
    public ResponseEntity<MongoDocuments> create(@RequestBody MongoDocuments doc){
        documentsRepository.save(doc);
        return ResponseEntity
                .status(201)
                .body(doc);
    }
}
