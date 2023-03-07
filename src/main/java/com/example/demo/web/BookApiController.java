package com.example.demo.web;

import com.example.demo.dto.response.BookListResponseDto;
import com.example.demo.dto.response.BookResponseDto;
import com.example.demo.dto.request.BookSaveRequestDto;
import com.example.demo.dto.response.CMRespDto;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    //1. 책등록
    @PostMapping("/api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody @Valid BookSaveRequestDto bookSaveRequestDto, BindingResult bindingResult) {
        log.info("error {}",bindingResult.hasErrors());

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            log.info("error {}",errorMap.toString());
            throw new RuntimeException(errorMap.toString());
        }
        BookResponseDto bookResponseDto=bookService.책등록하기(bookSaveRequestDto);
        return new ResponseEntity<>(CMRespDto.builder().code(1).msg("글 저장 성공").body(bookResponseDto).build(),HttpStatus.CREATED);
    }

    //2. 책목록보기
    @GetMapping("api/v1/book")
    public ResponseEntity<?> getBookList() {
        BookListResponseDto bookListResponseDto = bookService.책목록보기();
        return new ResponseEntity<>(CMRespDto.builder().code(1).msg("글 목록가져오기 성공").body(bookListResponseDto).build(),HttpStatus.OK);
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
