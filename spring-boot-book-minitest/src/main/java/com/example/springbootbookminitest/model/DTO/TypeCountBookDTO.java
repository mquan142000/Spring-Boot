package com.example.springbootbookminitest.model.DTO;

import lombok.Data;

@Data
public class TypeCountBookDTO {
    private Long id;
    private String typeName;
    private Long bookCount;

    public TypeCountBookDTO() {
    }

    public TypeCountBookDTO(Long id, String typeName, Long bookCount) {
        this.id = id;
        this.typeName = typeName;
        this.bookCount = bookCount;
    }
}
