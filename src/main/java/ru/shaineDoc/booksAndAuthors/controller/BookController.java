package ru.shaineDoc.booksAndAuthors.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shaineDoc.booksAndAuthors.dto.BookDto;
import ru.shaineDoc.booksAndAuthors.entity.Book;
import ru.shaineDoc.booksAndAuthors.mapper.BookMapper;
import ru.shaineDoc.booksAndAuthors.service.BookService;
import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
        Book book = BookMapper.toEntityBook(bookDto);
        Book bookSaved = bookService.createBook(book);
        BookDto bookDtoSaved = BookMapper.toBookDto(bookSaved);
        URI location = URI.create("/books/" + bookSaved.getId());
        return ResponseEntity.created(location).body(bookDtoSaved);

    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto, @PathVariable Long id) {
        Book book = BookMapper.toEntityBook(bookDto);
        Book bookUpdate = bookService.updateBook(id, book);
        BookDto bookDtoSaved = BookMapper.toBookDto(bookUpdate);
        return ResponseEntity.ok(bookDtoSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        Book findBook = bookService.findBookById(id);
        BookDto bookDto = BookMapper.toBookDto(findBook);
        return ResponseEntity.ok(bookDto);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Void> deleteBook (@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        }
        catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
