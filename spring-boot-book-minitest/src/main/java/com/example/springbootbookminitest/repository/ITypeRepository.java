package com.example.springbootbookminitest.repository;

import com.example.springbootbookminitest.model.Book;
import com.example.springbootbookminitest.model.DTO.ICountBook;
import com.example.springbootbookminitest.model.Type;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ITypeRepository extends JpaRepository<Type, Long> {
    @Modifying
    @Transactional

    @Query(nativeQuery = true, value = "SELECT type.name, count(book.id) as number FROM type LEFT JOIN book on type.id = type_id GROUP BY type.name;")
    Iterable<ICountBook> getCountBook();

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE type_id = :id")
    Page<Book> findByType(@Param("id") Long id, Pageable pageable);
}
