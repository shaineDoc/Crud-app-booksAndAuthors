package ru.shaineDoc.booksAndAuthors.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shaineDoc.booksAndAuthors.dto.AuthorDto;
import ru.shaineDoc.booksAndAuthors.dto.BookDto;
import ru.shaineDoc.booksAndAuthors.entity.Author;
import ru.shaineDoc.booksAndAuthors.entity.Book;
import ru.shaineDoc.booksAndAuthors.mapper.AuthorMapper;
import ru.shaineDoc.booksAndAuthors.mapper.BookMapper;
import ru.shaineDoc.booksAndAuthors.service.AuthorService;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {

    AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorDto) {
        Author author = AuthorMapper.toEntityAuthor(authorDto);
        Author authorSaved = authorService.create(author);
        AuthorDto authorDtoSaved = AuthorMapper.toAuthorDto(authorSaved);
        URI location = URI.create("/authors/" + authorSaved.getId());
        return ResponseEntity.created(location).body(authorDtoSaved);
    }

    @PostMapping("/create/all")
    public ResponseEntity<List<AuthorDto>> createListAuthors(@RequestBody List<AuthorDto> authorDtos) {
        List<Author> authors = authorService.saveAll(authorDtos);
        List<AuthorDto> authorDtosResponse = authors.stream()
                .map(AuthorMapper::toAuthorDto)
                .toList();
        return ResponseEntity.ok(authorDtosResponse);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<AuthorDto> update(@RequestBody AuthorDto authorDto, @PathVariable Long id) {
        Author author = AuthorMapper.toEntityAuthor(authorDto);
        Author authorUpdate = authorService.update(id, author);
        AuthorDto authorDtoSaved = AuthorMapper.toAuthorDto(authorUpdate);
        return ResponseEntity.ok(authorDtoSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable Long id) {
        Author findAuthor = authorService.findById(id);
        AuthorDto authorDto = AuthorMapper.toAuthorDto(findAuthor);
        return ResponseEntity.ok(authorDto);

    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDto>> getBooksAuthor(@PathVariable Long id) {
        List<Book> bookList = authorService.getBooksFromAuthor(id);
        List<BookDto> bookDtos = bookList.stream()
                .map(BookMapper::toBookDto)
                .toList();
        return ResponseEntity.ok(bookDtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            authorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}


