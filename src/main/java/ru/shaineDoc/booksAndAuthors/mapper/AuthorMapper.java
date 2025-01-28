package ru.shaineDoc.booksAndAuthors.mapper;

import ru.shaineDoc.booksAndAuthors.dto.AuthorDto;
import ru.shaineDoc.booksAndAuthors.entity.Author;

import java.util.stream.Collectors;

public class AuthorMapper {

    public static AuthorDto toAuthorDto(Author author) {
        if (author == null)
            return null;

        AuthorDto authorDto = new AuthorDto();
        authorDto.setName(author.getName());
        authorDto.setBirthDate(author.getBirthDate());
        if (author.getBooks()!= null) {
            authorDto.setBooks(author.getBooks().stream()
                    .map(BookMapper::toBookDto)
                    .collect(Collectors.toList()));
        }
        return authorDto;

    }

    public static Author toEntityAuthor(AuthorDto authorDto) {
        if (authorDto == null)
            return null;

        Author author = new Author();
        author.setName(authorDto.getName());
        author.setBirthDate(authorDto.getBirthDate());
        author.setBooks(authorDto.getBooks().stream()
                .map(BookMapper::toEntityBook)
                .collect(Collectors.toList()));
        return author;
    }

}
