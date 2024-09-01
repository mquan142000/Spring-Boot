package com.example.springbootbookminitest.service;

import com.example.springbootbookminitest.model.DTO.ICountBook;
import com.example.springbootbookminitest.model.Type;

public interface ITypeService extends IGenerateService<Type> {
    Iterable<ICountBook> getCountBook();

}
