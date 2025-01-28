package ru.shaineDoc.booksAndAuthors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shaineDoc.booksAndAuthors.entity.Book;


public interface BookRepository extends JpaRepository<Book, Long> {
}
