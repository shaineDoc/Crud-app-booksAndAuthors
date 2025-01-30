package ru.shaineDoc.booksAndAuthors.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.shaineDoc.booksAndAuthors.entity.Author;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {
    private String title;
    private int releaseYear;
    private Long authorId;


}
