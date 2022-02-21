package com.portfolio.shoppingmall.repository;

import com.portfolio.shoppingmall.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findBySlug(String slug);

    Product findBySlugAndIdNot(String slug, int id);

    Page<Product> findAll(Pageable pageable);

    List<Product> findByNameContaining(String keyword);

    List<Product> findAllByCategoryId(String catid, Pageable pageable);

    long countByCategoryId(String catid);
}