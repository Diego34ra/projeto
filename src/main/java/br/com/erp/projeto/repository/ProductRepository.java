package br.com.erp.projeto.repository;

import br.com.erp.projeto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
