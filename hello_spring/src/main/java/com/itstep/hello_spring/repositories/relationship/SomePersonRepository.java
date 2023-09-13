package com.itstep.hello_spring.repositories.relationship;

import com.itstep.hello_spring.models.relationship.one_many.SomePerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SomePersonRepository extends JpaRepository<SomePerson, UUID>
{
}
