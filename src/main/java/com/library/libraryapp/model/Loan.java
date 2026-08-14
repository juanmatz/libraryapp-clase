package com.library.libraryapp.model;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "loans")
public class Loan {

    @Id
    private String id;

    // BUENA PRÁCTICA: Guardar el ID plano de Book en vez de usar @DBRef.
    // @Indexed crea un indice secundario en MongoDB para agilizar la busqueda findByBookId()
    @Indexed
    private String bookId;

    private String userName;
    private LocalDate loanDate;
    private LocalDate returnDate;
}