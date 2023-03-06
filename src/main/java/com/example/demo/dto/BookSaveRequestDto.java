package com.example.demo.dto;

import com.example.demo.domain.Book;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookSaveRequestDto {
    private String title;
    private String author;

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }
}
