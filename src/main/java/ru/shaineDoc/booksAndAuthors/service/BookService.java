package ru.shaineDoc.booksAndAuthors.service;
/**
 * Поиск книг
 *
 *@param id индентификатор книги
 *@return найденной книги
 *@throws  если книга не существует EntityNotFoundException
 *
 */
import ru.shaineDoc.booksAndAuthors.entity.Book;
import java.util.List;



public interface BookService {

    Book createBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);

    Book findBookById(Long id);

    List<Book> getAllBooks();


}
