package com.library.libraryapp.controller;

import com.library.libraryapp.dto.LoanDTO;
import com.library.libraryapp.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // GET /api/loans - Obtener todos los prestamos
    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    // GET /api/loans/book/{bookId} - Obtener el historial de prestamos de un libro en concreto
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<LoanDTO>> getLoansByBookId(@PathVariable String bookId) {
        return ResponseEntity.ok(loanService.getLoansByBookId(bookId));
    }

    // POST /api/loans - Crear nuevo prestamo
    @PostMapping
    public ResponseEntity<?> createLoan(@RequestBody LoanDTO dto) {
        try {
            LoanDTO createdLoan = loanService.createLoan(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
        } catch (RuntimeException ex) {
            // Retorna un código 400 (Bad Request) o 404 con el mensaje exacto
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", ex.getMessage()));
        }
    }



    // DELETE /api/loans/{id} - Registrar devolucion / eliminar un prestamo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> returnLoan(@PathVariable String id) {
        loanService.returnLoan(id);
        return ResponseEntity.noContent().build();
    }
}
