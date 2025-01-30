package ru.shaineDoc.booksAndAuthors.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shaineDoc.booksAndAuthors.dto.AuthorDto;
import ru.shaineDoc.booksAndAuthors.entity.Author;
import ru.shaineDoc.booksAndAuthors.entity.Book;
import ru.shaineDoc.booksAndAuthors.mapper.AuthorMapper;
import ru.shaineDoc.booksAndAuthors.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;


    @Override
    public Author create(Author author) {
        if (author.getBooks() != null) {
            for (Book book : author.getBooks()) {
                book.setAuthor(author);
            }
        }
        return authorRepository.save(author);
    }

    @Override
    public List<Author> saveAll(List<AuthorDto> authorDtos) {
        List<Author> authors = authorDtos.stream()
                .map(AuthorMapper::toEntityAuthor)
                .toList();
        return authorRepository.saveAll(authors);
    }

    @Override
    public Author update(Long id, Author author) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Автор с таким ID не найден!"));
        if (author.getName() != null) {
            existingAuthor.setName(author.getName());
        }
        if (author.getBirthDate() != null) {
            existingAuthor.setBirthDate(author.getBirthDate());
        }
        if (author.getBooks() != null) {
            List<Book> updatedBooks = author.getBooks().stream()
                    .map(book -> {
                        book.setAuthor(existingAuthor);
                        return book;
                    })
                    .toList();
            existingAuthor.getBooks().clear();
            existingAuthor.getBooks().addAll(updatedBooks);
        }

        return authorRepository.save(existingAuthor);
    }

    @Override
    public void delete(Long id) {
        authorRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Автор c таким ID не найден!"));
        authorRepository.deleteById(id);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Автор c таким ID не найден!"));
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }


    @Override
    public void addBookToAuthor(long id, Book book) {
        Author author = authorRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Автор c таким ID не найден!"));
        author.getBooks().add(book);
        authorRepository.save(author);

    }

    @Override
    public List<Book> getBooksFromAuthor(long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Автор c таким ID не найден!"));
        return author.getBooks();
    }


}
