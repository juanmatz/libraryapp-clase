package com.library.libraryapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {

    private String id;

    // Rechaza cadenas vacias, nulas o con solo espacios
    @NotBlank(message = "El titulo no puede estar vacio")
    private String title;

    @NotBlank(message = "El autor es obligatorio")
    private String author;

    // Aplica expresion regular para validar formato valido de ISBN-10 o ISBN-13
    @NotBlank(message = "El ISBN no puede estar vacio")
    @Pattern(regexp = "^(?:\\d{9}[\\dX]|\\d{13})$", message = "Formato de ISBN no valido (ej. 9780307474728)")
    private String isbn;

    private boolean available = true;}

