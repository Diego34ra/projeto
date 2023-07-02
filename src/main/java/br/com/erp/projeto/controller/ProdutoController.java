package br.com.erp.projeto.controller;

import br.com.erp.projeto.dtos.request.MessageResponseDTO;
import br.com.erp.projeto.exceptions.ResourceNotFoundException;
import br.com.erp.projeto.model.Product;
import br.com.erp.projeto.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProdutoController {

    @Autowired
    private ProductService productService;

    /**
     * Busca uma lista de todos os produtos
     * @param authHeader token de autorizacao
     * @return retorna uma lista de produtos
     */
    @GetMapping
    public ResponseEntity<List<Product>> findAll(@RequestHeader("Authorization") String authHeader){
        var productList = productService.findAll();
        return ResponseEntity.ok().body(productList);
    }

    /**
     * Busca um produto através do id
     * @param authHeader token de autorizacao
     * @param id id do produto
     * @return retorna um unico produto
     * @throws ResourceNotFoundException excecao caso um produto nao seja encontrado
     */
    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id) throws ResourceNotFoundException {
        var product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createProduct(@RequestHeader("Authorization") String authHeader, @RequestBody Product product){
        var message = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("{id}")
    public ResponseEntity<MessageResponseDTO> updateProduct(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id, @RequestBody Product product) throws ResourceNotFoundException {
        var message = productService.updateProduct(id,product);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponseDTO> deleteProduct(@RequestHeader("Authorization") String authHeader, @PathVariable Integer id) throws ResourceNotFoundException {
        var message = productService.deleteById(id);
        return ResponseEntity.ok().body(message);
    }
}
