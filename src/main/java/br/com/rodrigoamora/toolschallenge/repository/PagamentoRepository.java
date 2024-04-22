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
	Page<Pagamento> findAll(Pageable pageable);
	
	@Query("FROM Pagamento p, Descricao d WHERE d.status = br.com.rodrigoamora.toolschallenge.entity.StatusPagamento.AUTORIZADO AND p.id = d.id")
	Page<Pagamento> buscarPagamentoComStatusAutorizado(Pageable pageable);
	
	@Query("FROM Pagamento p, Descricao d WHERE d.status = br.com.rodrigoamora.toolschallenge.entity.StatusPagamento.CANCELADO AND p.id = d.id")
	Page<Pagamento> buscarPagamentoComStatusCancelado(Pageable pageable);
	
	@Query("FROM Pagamento p, Descricao d WHERE d.status = br.com.rodrigoamora.toolschallenge.entity.StatusPagamento.NEGADO AND p.id = d.id")
	Page<Pagamento> buscarPagamentoComStatusNegado(Pageable pageable);
	
}
