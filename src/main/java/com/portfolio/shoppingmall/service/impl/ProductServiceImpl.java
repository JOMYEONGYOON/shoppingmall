package com.portfolio.shoppingmall.service.impl;

import com.portfolio.shoppingmall.domain.Product;
import com.portfolio.shoppingmall.repository.ProductRepository;
import com.portfolio.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {

        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByNameContaining(String keyword) {
        return null;
    }
}
