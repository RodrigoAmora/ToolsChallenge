package br.com.rodrigoamora.toolschallenge.service;

import org.springframework.data.domain.Page;

import br.com.rodrigoamora.toolschallenge.entity.Pagamento;

public interface PagamentoService {

	public Pagamento realizarPagamento(Pagamento pagamento);
	public Page<Pagamento> listarTodos(int page, int size);
	public Pagamento buscarPagamentoPorId(Long pagamentoId);
	public Pagamento estonarPagamento(Pagamento pagamento);
	
	public Page<Pagamento> buscarPagamentoComStatusAutorizado(int page, int size);
	public Page<Pagamento> buscarPagamentoComStatusCancelado(int page, int size);
	public Page<Pagamento> buscarPagamentoComStatusNegado(int page, int size);
	
}
