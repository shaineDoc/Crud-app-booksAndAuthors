package ru.shaineDoc.booksAndAuthors.service;
/**
 * Поиск авторов по Id
 *
 * @param id индентификатор автора
 * @return найденного автора
 * @throws если автор не найден EntityNotFoundException
 *
 */

import ru.shaineDoc.booksAndAuthors.entity.Author;
import ru.shaineDoc.booksAndAuthors.entity.Book;
import java.util.List;


public interface AuthorService {

    Author create(Author author);

    Author update(Long id, Author author);

    void delete(Long id);

    List<Author> getAll();

    Author findById(Long id);

    void addBookToAuthor(long id, Book book);

    List<Book> getBooksFromAuthor(long id);


}
