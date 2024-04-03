package br.com.rodrigoamora.toolschallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodrigoamora.toolschallenge.entity.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
