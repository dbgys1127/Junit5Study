package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.domain.BookRepository;
import com.example.demo.dto.response.BookListResponseDto;
import com.example.demo.dto.response.BookResponseDto;
import com.example.demo.dto.request.BookSaveRequestDto;
import com.example.demo.util.MailSender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
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
        BookListResponseDto bookListResponseDto = bookService.책목록보기();


        //then(검증)
        assertThat(bookListResponseDto.getItems().get(0).getTitle()).isEqualTo("junit강의");
        assertThat(bookListResponseDto.getItems().get(0).getAuthor()).isEqualTo("메타코딩");
        assertThat(bookListResponseDto.getItems().get(0).getTitle()).isEqualTo("spring강의");
        assertThat(bookListResponseDto.getItems().get(0).getAuthor()).isEqualTo("유현");
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
    @Test
    public void 책수정하기_테스트() {
        //given
        Long id = 1L;
        BookSaveRequestDto dto = new BookSaveRequestDto();
        dto.setTitle("spring");
        dto.setAuthor("dbgys1127");

        //stub
        Book book = Book.builder()
                .id(1L)
                .title("junit")
                .author("dbgys")
                .build();
        Optional<Book> bookOP = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(bookOP);

        //when
        BookResponseDto bookResponseDto=bookService.책수정하기(id, dto);

        //then
        assertThat(bookResponseDto.getTitle()).isEqualTo("spring");
        assertThat(bookResponseDto.getAuthor()).isEqualTo("dbgys1127");
    }
}
