package ru.shaineDoc.booksAndAuthors.mapper;

import lombok.Getter;
import lombok.Setter;
import ru.shaineDoc.booksAndAuthors.dto.AuthorDto;
import ru.shaineDoc.booksAndAuthors.entity.Author;
import ru.shaineDoc.booksAndAuthors.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AuthorMapper {

    public static AuthorDto toAuthorDto(Author author) {
        if (author == null) return null;

        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        authorDto.setBirthDate(author.getBirthDate());

        if (author.getBooks() != null) {
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
        author.setId(authorDto.getId());

        if (authorDto.getBooks() != null) {
            List<Book> books = authorDto.getBooks().stream()
                    .map(bookDto -> {
                        Book book = BookMapper.toEntityBook(bookDto);
                        book.setAuthor(author);
                        return book;
                    })
                    .collect(Collectors.toList());
            author.setBooks(books);
        }

        return author;
    }

}
