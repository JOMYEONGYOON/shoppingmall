package com.portfolio.shoppingmall.service;

import com.portfolio.shoppingmall.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(int id);

    List<Product> findByNameContaining(String keyword);

    List<Product> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    Page<Product> findAll(Pageable pageable);


}
