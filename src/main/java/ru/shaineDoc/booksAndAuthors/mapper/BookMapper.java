package ru.shaineDoc.booksAndAuthors.mapper;

import ru.shaineDoc.booksAndAuthors.dto.BookDto;
import ru.shaineDoc.booksAndAuthors.entity.Book;

public class BookMapper {

    public static BookDto toBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setReleaseYear(book.getReleaseYear());
        if (book.getAuthor() != null) {
            bookDto.setAuthorDto(AuthorMapper.toAuthorDto(book.getAuthor()));
        }
        return bookDto;
    }

    public static Book toEntityBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setReleaseYear(bookDto.getReleaseYear());
        if (bookDto.getAuthorDto() != null) {
            book.setAuthor(AuthorMapper.toEntityAuthor(bookDto.getAuthorDto()));
        }
        return book;
    }
}
