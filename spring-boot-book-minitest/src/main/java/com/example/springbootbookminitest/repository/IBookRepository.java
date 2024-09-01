package com.example.springbootbookminitest.repository;

import com.example.springbootbookminitest.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface IBookRepository extends CrudRepository<Book, Long> {
    Page<Book> findAllByNameContaining(Pageable pageable, String name);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findByTypeId(Long typeId, Pageable pageable);
}
