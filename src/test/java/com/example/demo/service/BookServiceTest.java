package com.example.demo.service;

import com.example.demo.domain.BookRepository;
import com.example.demo.dto.BookResponseDto;
import com.example.demo.dto.BookSaveRequestDto;
import com.example.demo.util.MailSender;
import com.example.demo.util.MailSenderStub;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
        Assertions.assertThat(bookResponseDto.getTitle()).isEqualTo(dto.getTitle());
        Assertions.assertThat(bookResponseDto.getAuthor()).isEqualTo(dto.getAuthor());
    }
}
