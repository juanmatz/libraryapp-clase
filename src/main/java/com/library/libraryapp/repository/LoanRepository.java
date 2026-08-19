package com.library.libraryapp.repository;

import com.library.libraryapp.model.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LoanRepository extends MongoRepository<Loan,String> {
    List<Loan> findByBookId(String book);
}
