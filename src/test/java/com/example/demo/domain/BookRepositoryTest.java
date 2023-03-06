package com.example.demo.domain;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로 -> 컨트롤러와 서비스 계층은 메모리에 로드되지 않는다.
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void 데이터준비() {
        log.info("==============================");
        String title = "junit";
        String author = "dbgys";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        //when
        bookRepository.save(book);
    }// 트랜잭션 종료 됐다면 말이안된다
    // 가정 1 : {데이터준비() + 1.책등록 [T]}, {데이터준비() + 2.책목록보기 [T]} -> 이게 맞다
    // 가정 2 : {데이터준비() + 1.책등록 + 데이터준비() + 2.책목록보기 [T]}

    // 1. 책 등록
    @Test
    public void 책등록_test() {
        //given (데이터 준비)
        String title = "junit5";
        String author = "유현";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        //when (테스트 실행)
        Book bookPS = bookRepository.save(book);

        // then(검증)
        assertThat(bookPS.getTitle()).isEqualTo("junit5");
        assertThat(bookPS.getAuthor()).isEqualTo("유현");
    }


    // 2. 책 목록 보기
    @Test
    public void 책목록보기_test() {
        //given
        String title = "junit";
        String author = "dbgys";

        //when
        List<Book> books = bookRepository.findAll();

        log.info("사이즈 ======================= {}",books.size());
        //가정 1. -> 사이즈 1  -> 이게 맞다
        //가정 2. -> 사이즈 2

        //then
        assertThat(books.get(0).getTitle()).isEqualTo(title);
        assertThat(books.get(0).getAuthor()).isEqualTo(author);
    }
    // 3. 책 한건 보기
    @Test
    @Sql("classpath:db/tableInit.sql")
    public void 책한건보기_test() {
        String title = "junit";
        String author = "dbgys";

        //when
        Book bookPS = bookRepository.findById(1L).get();

        //then
        assertThat(bookPS.getTitle()).isEqualTo(title);
        assertThat(bookPS.getAuthor()).isEqualTo(author);
    }

    // 4. 책 삭제
    @Test
    @Sql("classpath:db/tableInit.sql")
    public void 책삭제_test() {
        //given
        Long id = 1L;

        //when
        bookRepository.deleteById(id);

        //then
        Optional<Book> bookPS = bookRepository.findById(id);
        assertThat(bookPS).isEmpty();
    }


    // 5. 책 수정
}
