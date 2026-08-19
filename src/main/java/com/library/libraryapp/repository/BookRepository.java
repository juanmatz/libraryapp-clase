package com.library.libraryapp.repository;

import com.library.libraryapp.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
