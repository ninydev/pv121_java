package com.itstep.hello_spring.repositories;

import com.itstep.hello_spring.models.mongo.MongoDocuments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDocumentsRepository extends MongoRepository<MongoDocuments, String>
{
}
