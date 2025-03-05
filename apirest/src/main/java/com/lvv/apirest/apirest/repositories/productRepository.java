package com.lvv.apirest.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lvv.apirest.apirest.entities.product;

public interface productRepository extends JpaRepository<product, Long> {

}
