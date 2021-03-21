package com.website.demo.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookRepository {
    private final EntityManager entityManager;

    @Transactional
    public List<BookEntity> allBooks() {
        return entityManager.createQuery("FROM BookEntity", BookEntity.class)
                .getResultList();
    }

    @Transactional
    public BookEntity getBookById(final Integer id) {
        return entityManager.find(BookEntity.class, id);
    }

    @Transactional
    public List<BookEntity> findBySearchKeyword(final String searchKeyword) {

        return entityManager.createQuery("FROM BookEntity where isbn like :query or title like :query", BookEntity.class)
                .setParameter("query", "%"+searchKeyword+"%")
                .getResultList();
    }

    @Transactional
    public BookEntity addBook(final BookEntity newBook){
        return entityManager.merge(newBook);
    }

}
