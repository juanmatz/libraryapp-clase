package com.library.libraryapp.service;

import com.library.libraryapp.dto.BookDTO;
import com.library.libraryapp.model.Book;
import com.library.libraryapp.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // Inyeccion de dependencias explicita por constructor
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con el ID: " + id));
        return convertToDTO(book);
    }

    public BookDTO createBook(BookDTO dto) {
        Book book = convertToEntity(dto);
        Book saved = bookRepository.save(book);
        return convertToDTO(saved);
    }

    public BookDTO updateBook(String id, BookDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con el ID: " + id));

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setAvailable(dto.isAvailable());

        Book updated = bookRepository.save(book);
        return convertToDTO(updated);
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    // Metodos auxiliares de mapeo
    private BookDTO convertToDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.isAvailable());
    }

    private Book convertToEntity(BookDTO dto) {
        return new Book(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.getIsbn(), dto.isAvailable());
    }
}