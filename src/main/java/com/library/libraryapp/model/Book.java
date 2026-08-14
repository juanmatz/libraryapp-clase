package com.library.libraryapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Mapea la clase a la coleccion "books" en MongoDB
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {

    // Identificador unico autogenerado por MongoDB (ObjectId)
    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;

    // Indicador de disponibilidad para controlar la regla de negocio al prestar
    private boolean available = true;
}