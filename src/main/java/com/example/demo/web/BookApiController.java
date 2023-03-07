package com.example.demo.web;

import com.example.demo.dto.response.BookResponseDto;
import com.example.demo.dto.request.BookSaveRequestDto;
import com.example.demo.dto.response.CMRespDto;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    //1. 책등록
    @PostMapping("/api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody BookSaveRequestDto bookSaveRequestDto) {
        BookResponseDto bookResponseDto=bookService.책등록하기(bookSaveRequestDto);
        CMRespDto<?> cmRespDto = CMRespDto.builder().code(1).msg("글 저장 성공").body(bookResponseDto).build();
        return new ResponseEntity<>(cmRespDto,HttpStatus.CREATED);
    }

    //2. 책목록보기
    public ResponseEntity<?> getBookList() {
        return null;
    }
    //3. 책한건보기
    public ResponseEntity<?> getBookOne() {
        return null;
    }
    //4. 책삭제하기
    public ResponseEntity<?> deleteBook() {
        return null;
    }
    //5. 책수정하기
    public ResponseEntity<?> updateBook() {
        return null;
    }
}
