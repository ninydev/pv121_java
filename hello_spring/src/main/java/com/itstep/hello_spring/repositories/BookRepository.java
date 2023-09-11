package com.itstep.hello_spring.repositories;

import com.itstep.hello_spring.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Правила для реализации репозитория для сущности
 */
@Repository
public interface BookRepository extends JpaRepository<Book, UUID>
{
}
