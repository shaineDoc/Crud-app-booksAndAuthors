package ru.shaineDoc.mapper;

import org.junit.jupiter.api.Test;
import ru.shaineDoc.booksAndAuthors.dto.BookDto;
import ru.shaineDoc.booksAndAuthors.entity.Book;
import ru.shaineDoc.booksAndAuthors.entity.Author;
import ru.shaineDoc.booksAndAuthors.mapper.BookMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    @Test
    void testToBookDto() {
        // Подготовка данных
        Author author = new Author();
        author.setName("John Doe");
        author.setBirthDate(LocalDate.of(1980, 1, 1));

        Book book = new Book();
        book.setTitle("Test Book");
        book.setReleaseYear(2020);
        book.setAuthor(author);

        // Вызов метода
        BookDto bookDto = BookMapper.toBookDto(book);

        // Проверка результатов
        assertNotNull(bookDto);
        assertEquals("Test Book", bookDto.getTitle());
        assertEquals(2020, bookDto.getReleaseYear());
        assertNotNull(bookDto.getAuthorDto());
        assertEquals("John Doe", bookDto.getAuthorDto().getName());
        assertEquals(LocalDate.of(1980, 1, 1), bookDto.getAuthorDto().getBirthDate());
    }

    @Test
    void testToEntityBook() {
        // Подготовка данных
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Test Book");
        bookDto.setReleaseYear(2020);

        // Вызов метода
        Book book = BookMapper.toEntityBook(bookDto);

        // Проверка результатов
        assertNotNull(book);
        assertEquals("Test Book", book.getTitle());
        assertEquals(2020, book.getReleaseYear());
    }
}