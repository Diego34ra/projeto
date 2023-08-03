package br.com.erp.projeto.repository;

import br.com.erp.projeto.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {
}
