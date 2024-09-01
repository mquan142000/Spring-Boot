package com.example.springbootbookminitest.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private double price;
    private String image;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
}
