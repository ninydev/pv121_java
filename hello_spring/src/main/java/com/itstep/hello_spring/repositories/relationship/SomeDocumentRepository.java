package com.itstep.hello_spring.repositories.relationship;

import com.itstep.hello_spring.models.relationship.one_many.SomeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.UUID;

@Repository
public interface SomeDocumentRepository extends JpaRepository<SomeDocument, UUID>
{

//    @Query("SELECT d FROM SomeDocument d WHERE d.person.id = :person_id")
//    HashSet<SomeDocument> findDocumentsByPersonId(@Param("person_id") UUID person_id);

}
