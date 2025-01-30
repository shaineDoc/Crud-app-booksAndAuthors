package ru.shaineDoc.booksAndAuthors.mapper;

import lombok.Getter;
import lombok.Setter;
import ru.shaineDoc.booksAndAuthors.dto.BookDto;
import ru.shaineDoc.booksAndAuthors.entity.Author;
import ru.shaineDoc.booksAndAuthors.entity.Book;

@Getter
@Setter
public class BookMapper {

    public static BookDto toBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setReleaseYear(book.getReleaseYear());
        if (book.getAuthor() != null) {
            bookDto.setAuthorId(book.getAuthor().getId());
        }
        return bookDto;
    }

    public static Book toEntityBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setReleaseYear(bookDto.getReleaseYear());
        if (bookDto.getAuthorId() != null) {
            Author author = new Author();
            author.setId(bookDto.getAuthorId());
            book.setAuthor(author);

        }
        return book;
    }
}
