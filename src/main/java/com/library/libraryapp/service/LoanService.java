package com.library.libraryapp.service;

import com.library.libraryapp.dto.LoanDTO;
import com.library.libraryapp.model.Book;
import com.library.libraryapp.model.Loan;
import com.library.libraryapp.repository.BookRepository;
import com.library.libraryapp.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Consulta el historial de prestamos especifico para un libro usando la firma personalizada
    public List<LoanDTO> getLoansByBookId(String bookId) {
        return loanRepository.findByBookId(bookId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LoanDTO createLoan(LoanDTO dto) {
        // 1. BUENA PRÁCTICA: Validar en Java la existencia del libro (integridad referencial manual)
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Error: No existe ningun libro con el ID proporcionado: " + dto.getBookId()));

        // 2. Validar regla de negocio de disponibilidad
        if (!book.isAvailable()) {
            throw new RuntimeException("El libro '" + book.getTitle() + "' no esta disponible para prestamo en este momento");
        }

        // 3. Modificar estado del libro y guardar
        book.setAvailable(false);
        bookRepository.save(book);

        // 4. Guardar el nuevo prestamo referenciando Unicamente el ID del libro
        Loan loan = convertToEntity(dto);
        Loan savedLoan = loanRepository.save(loan);

        return convertToDTO(savedLoan);
    }

    public void returnLoan(String loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado con el ID: " + loanId));

        // Restablecer la disponibilidad del libro al procesar la devolucion/eliminacion
        bookRepository.findById(loan.getBookId()).ifPresent(book -> {
            book.setAvailable(true);
            bookRepository.save(book);
        });

        loanRepository.deleteById(loanId);
    }

    private LoanDTO convertToDTO(Loan loan) {
        return new LoanDTO(loan.getId(), loan.getBookId(), loan.getUserName(), loan.getLoanDate(), loan.getReturnDate());
    }

    private Loan convertToEntity(LoanDTO dto) {
        return new Loan(dto.getId(), dto.getBookId(), dto.getUserName(), dto.getLoanDate(), dto.getReturnDate());
    }
}

