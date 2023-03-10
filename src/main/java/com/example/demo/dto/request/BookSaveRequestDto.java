package com.example.demo.dto.request;

import com.example.demo.domain.Book;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class BookSaveRequestDto {
    @Size(min=1,max=50)
    @NotBlank
    private String title;
    @Size(min=2,max=20)
    @NotBlank
    private String author;

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }
}
