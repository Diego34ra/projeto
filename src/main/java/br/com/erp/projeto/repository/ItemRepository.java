package br.com.erp.projeto.repository;

import br.com.erp.projeto.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
