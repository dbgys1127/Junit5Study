package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.domain.BookRepository;
import com.example.demo.dto.BookResponseDto;
import com.example.demo.dto.BookSaveRequestDto;
import com.example.demo.util.MailSender;
import com.example.demo.util.MailSenderStub;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MailSender mailSender;
    @Test
    public void 책등록하기_테스트() {
        //given
        BookSaveRequestDto dto = new BookSaveRequestDto();
        dto.setTitle("junit");
        dto.setAuthor("메타코딩");

        //stub(가설)
        when(bookRepository.save(any())).thenReturn(dto.toEntity());
        when(mailSender.send()).thenReturn(true);

        //when
        BookResponseDto bookResponseDto = bookService.책등록하기(dto);

        //then
        assertThat(bookResponseDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookResponseDto.getAuthor()).isEqualTo(dto.getAuthor());
    }

    @Test
    public void 책목록보기_테스트() {
        //given(파라미터로 들어로 데이터)

        //stub(가설)
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L,"junit강의","메타코딩"));
        books.add(new Book(2L,"spring강의","유현"));
        when(bookRepository.findAll()).thenReturn(books);

        //when(실행)
        List<BookResponseDto> bookResponseDtoList = bookService.책목록보기();

        //log
        bookResponseDtoList.stream().forEach((dto)->{
            log.info("dto.getId() = {}",dto.getId());
        });

        //then(검증)
        assertThat(bookResponseDtoList.get(0).getTitle()).isEqualTo("junit강의");
        assertThat(bookResponseDtoList.get(0).getAuthor()).isEqualTo("메타코딩");
        assertThat(bookResponseDtoList.get(1).getTitle()).isEqualTo("spring강의");
        assertThat(bookResponseDtoList.get(1).getAuthor()).isEqualTo("유현");
    }

    @Test
    public void 책한건보기_테스트() {
        //given
        Long id = 1L;
        Book book = new Book(1L,"junit","dbgus");
        Optional<Book> bookOP = Optional.of(book);

        //stub
        when(bookRepository.findById(id)).thenReturn(bookOP);

        //when
        BookResponseDto bookResponseDto = bookService.책한건보기(id);

        //then
        assertThat(bookResponseDto.getTitle()).isEqualTo("junit");
        assertThat(bookResponseDto.getAuthor()).isEqualTo("dbgus");
    }
}
