package ru.shaineDoc.booksAndAuthors.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shaineDoc.booksAndAuthors.entity.Author;
import ru.shaineDoc.booksAndAuthors.entity.Book;
import ru.shaineDoc.booksAndAuthors.repository.AuthorRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;


    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Long id, Author author) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Автор c таким ID не найден!"));
        existingAuthor.setName(author.getName());
        existingAuthor.setBirthDate(author.getBirthDate());
        existingAuthor.setBooks(author.getBooks());
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
