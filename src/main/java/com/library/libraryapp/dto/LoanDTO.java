package com.library.libraryapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    private String id;

    // Obliga a proporcionar el ID plano del libro
    @NotBlank(message = "El ID del libro es obligatorio")
    private String bookId;

    @NotBlank(message = "El nombre del usuario que solicita el prestamo es obligatorio")
    private String userName;

    // Asegura que la fecha contenga un valor no nulo
    @NotNull(message = "La fecha de prestamo es obligatoria")
    private LocalDate loanDate;

    private LocalDate returnDate;
}