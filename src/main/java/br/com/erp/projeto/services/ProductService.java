package br.com.erp.projeto.services;

import br.com.erp.projeto.dtos.MessageResponseDTO;
import br.com.erp.projeto.exceptions.ResourceNotFoundException;
import br.com.erp.projeto.model.Product;
import br.com.erp.projeto.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll(){
        List<Product> productList= productRepository.findAll();
        return productList;
    }

    public Product findById(Integer id) throws ResourceNotFoundException {
        Product product = verificaSeExiste(id);
        return product;
    }

    public MessageResponseDTO createProduct(Product product){
        productRepository.save(product);
        return MessageResponseDTO
                .builder()
                .code(201)
                .status("Created")
                .message("Produto criado com sucesso.")
                .build();
    }

    public Product verificaSeExiste(Integer id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com id "+id+" não foi encontrado."));
        return product;
    }

    public MessageResponseDTO updateProduct(Integer id, Product productUpdate) throws ResourceNotFoundException {
        Product productAntigo = verificaSeExiste(id);
        if(productUpdate.getEan()!=null)
            productAntigo.setEan(productUpdate.getEan());
        if(productUpdate.getName()!=null)
            productAntigo.setName(productUpdate.getName());
        if(productUpdate.getPrice()!=null)
            productAntigo.setPrice(productUpdate.getPrice());
        if(productUpdate.getStock()!=null)
            productAntigo.setStock(productUpdate.getStock());
        if(productUpdate.getQtUnit()!=null)
            productAntigo.setQtUnit(productUpdate.getQtUnit());
        productRepository.save(productAntigo);
        return MessageResponseDTO
                .builder()
                .code(200)
                .status("Ok")
                .message("Produto atualizado com sucesso")
                .build();
    }

    public MessageResponseDTO deleteById(Integer id) throws ResourceNotFoundException {
        productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Produto com o id "+id+" não foi encontrado."));
        productRepository.deleteById(id);
        return MessageResponseDTO
                .builder()
                .code(200)
                .status("Ok")
                .message("Produto deletado com sucesso.")
                .build();
    }

}
