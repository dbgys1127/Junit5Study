package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 20,nullable = false)
    private String author;

    @Builder
    public Book(long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}