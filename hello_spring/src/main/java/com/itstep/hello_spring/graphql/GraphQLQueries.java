package com.itstep.hello_spring.graphql;

import com.itstep.hello_spring.models.Book;
import com.itstep.hello_spring.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLQueries {
    @Autowired
    private BookRepository bookRepository;


    public List<Book> getBooks (){
        return bookRepository.findAll();
    }
}
