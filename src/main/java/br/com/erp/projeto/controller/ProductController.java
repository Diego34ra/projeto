package br.com.erp.projeto.controller;

import br.com.erp.projeto.dtos.MessageResponseDTO;
import br.com.erp.projeto.exceptions.ResourceNotFoundException;
import br.com.erp.projeto.model.Product;
import br.com.erp.projeto.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(@RequestHeader("Authorization") String authHeader){
        var productList = productService.findAll();
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id) throws ResourceNotFoundException {
        var product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createProduct(@RequestHeader("Authorization") String authHeader, @RequestBody Product product){
        var message = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
