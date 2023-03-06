package com.example.demo.domain;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로 -> 컨트롤러와 서비스 계층은 메모리에 로드되지 않는다.
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

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
        Assertions.assertThat(bookPS.getTitle()).isEqualTo("junit5");
        Assertions.assertThat(bookPS.getAuthor()).isEqualTo("유현");
    }


    // 2. 책 목록 보기

    // 3. 책 한건 보기

    // 4. 책 수정

    // 5. 책 삭제
}
