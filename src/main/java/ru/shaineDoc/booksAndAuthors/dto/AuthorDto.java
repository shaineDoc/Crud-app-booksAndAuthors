package ru.shaineDoc.booksAndAuthors.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.shaineDoc.booksAndAuthors.entity.Book;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private List<BookDto> books;


}
