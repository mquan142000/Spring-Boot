package com.example.springbootbookminitest.service.impl;

import com.example.springbootbookminitest.model.DTO.ICountBook;
import com.example.springbootbookminitest.model.Type;
import com.example.springbootbookminitest.repository.ITypeRepository;
import com.example.springbootbookminitest.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeService implements ITypeService {
    @Autowired
    private ITypeRepository iTypeRepository;


    @Override
    public Page<Type> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<Type> findAll() {
        return iTypeRepository.findAll();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return iTypeRepository.findById(id);
    }

    @Override
    public void save(Type type) {
        iTypeRepository.save(type);
    }

    @Override
    public void remove(Long id) {
        iTypeRepository.deleteById(id);
    }

    @Override
    public Iterable<ICountBook> getCountBook() {
        return iTypeRepository.getCountBook();
    }

}
