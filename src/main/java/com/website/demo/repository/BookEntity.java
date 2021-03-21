package com.website.demo.repository;

import lombok.*;

import javax.persistence.*;


@Builder
@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String isbn;

    @Column
    private String title;

    @Column
    private String author;

}
