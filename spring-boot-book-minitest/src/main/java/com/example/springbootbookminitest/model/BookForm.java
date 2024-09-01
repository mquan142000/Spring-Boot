package com.example.springbootbookminitest.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BookForm {
    private Long id;
    private String name;
    private String author;
    private double price;
    private Type type;
    private MultipartFile image;

    public BookForm() {
    }

    public BookForm(Long id, String name, String author, double price, Type type, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.type = type;
        this.image = image;
    }
}
