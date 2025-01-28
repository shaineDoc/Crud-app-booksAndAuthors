package ru.shaineDoc.booksAndAuthors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shaineDoc.booksAndAuthors.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
