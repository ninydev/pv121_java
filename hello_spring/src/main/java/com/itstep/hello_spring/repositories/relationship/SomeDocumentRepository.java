package com.itstep.hello_spring.repositories.relationship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SomeDocumentRepository extends JpaRepository<SomeDocumentRepository, UUID>
{
}
