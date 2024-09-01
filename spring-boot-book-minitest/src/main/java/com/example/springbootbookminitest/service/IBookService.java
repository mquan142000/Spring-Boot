package com.example.springbootbookminitest.service;

import com.example.springbootbookminitest.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService extends IGenerateService<Book> {
    Page<Book> findAllByNameContaining(Pageable pageable, String name);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findByTypeId(Long typeId, Pageable pageable);
}
