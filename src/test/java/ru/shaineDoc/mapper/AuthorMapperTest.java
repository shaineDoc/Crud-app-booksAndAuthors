package ru.shaineDoc.mapper;

import org.junit.jupiter.api.Test;
import ru.shaineDoc.booksAndAuthors.dto.AuthorDto;
import ru.shaineDoc.booksAndAuthors.entity.Author;
import ru.shaineDoc.booksAndAuthors.mapper.AuthorMapper;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class AuthorMapperTest {

    @Test
    void testToAuthorDto() {
        // Подготовка данных
        Author author = new Author();
        author.setName("Jane Doe");
        author.setBirthDate(LocalDate.of(1990, 5, 15));

        // Вызов метода
        AuthorDto authorDto = AuthorMapper.toAuthorDto(author);

        // Проверка результатов
        assertNotNull(authorDto);
        assertEquals("Jane Doe", authorDto.getName());
        assertEquals(LocalDate.of(1990, 5, 15), authorDto.getBirthDate());
    }

    @Test
    void testToEntityAuthor() {
        // Подготовка данных
        AuthorDto authorDto = new AuthorDto();
        authorDto.setName("Jane Doe");
        authorDto.setBirthDate(LocalDate.of(1990, 5, 15));

        // Вызов метода
        Author author = AuthorMapper.toEntityAuthor(authorDto);

        // Проверка результатов
        assertNotNull(author);
        assertEquals("Jane Doe", author.getName());
        assertEquals(LocalDate.of(1990, 5, 15), author.getBirthDate());
    }
}