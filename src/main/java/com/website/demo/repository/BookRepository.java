package com.website.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {

    List<BookEntity> findAll();

    Optional<BookEntity> findById(final Integer id);

    @Query("FROM BookEntity where isbn like %:keyword% or title like %:keyword%")
    List<BookEntity> findByKeyword(@Param("keyword") final String keyword);

}
