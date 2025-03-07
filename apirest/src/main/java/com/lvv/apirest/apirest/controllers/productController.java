package com.lvv.apirest.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvv.apirest.apirest.entities.product;
import com.lvv.apirest.apirest.repositories.productRepository;

@RestController
@RequestMapping("/products")
public class productController {

  @Autowired
  private productRepository productRepository;

  @GetMapping
  public List<product> getAllProducts() {
    return productRepository.findAll();
  }

  @GetMapping("/{id}")
  public product getProductById(@PathVariable Long id) {
    return productRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
  }

  @PostMapping
  public product createProduct(@RequestBody product product) {
    return productRepository.save(product);
  }

  @PutMapping("/{id}")
  public product updateProduct(@PathVariable Long id, @RequestBody product productDetails) {
    product product = productRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

    product.setName(productDetails.getName());
    product.setPrice(productDetails.getPrice());
    product.setDescription(productDetails.getDescription());

    return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
      product product = productRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
      productRepository.deleteById(id);
      return "the product with Id: "+ id + " has been deleted successfully " + product;
    }

}
