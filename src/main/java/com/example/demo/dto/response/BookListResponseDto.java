package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BookListResponseDto {
    List<BookResponseDto> items;

    @Builder
    public BookListResponseDto(List<BookResponseDto> bookList) {
        this.items = bookList;
    }
}
