package com.example.springbootbookminitest.service.impl;

import com.example.springbootbookminitest.model.Book;
import com.example.springbootbookminitest.repository.IBookRepository;
import com.example.springbootbookminitest.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository iBookRepository;


    @Override
    public Page<Book> findAll(Pageable pageable) {
        return iBookRepository.findAll(pageable);
    }

    @Override
    public Iterable<Book> findAll() {
        return iBookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return iBookRepository.findById(id);
    }

    @Override
    public void save(Book book) {
        iBookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        iBookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findAllByNameContaining(Pageable pageable, String name) {
        return iBookRepository.findAllByNameContaining(pageable, name);
    }

    @Override
    public Page<Book> findByTypeId(Long typeId, Pageable pageable) {
        return iBookRepository.findByTypeId(typeId, pageable);
    }
}
