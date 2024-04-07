package br.com.rodrigoamora.toolschallenge.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.rodrigoamora.toolschallenge.entity.Pagamento;


@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
	
	@Query("FROM Pagamento p")
	Page<Pagamento> search(Pageable pageable);
	
}
