package br.com.rodrigoamora.toolschallenge.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigoamora.toolschallenge.entity.Pagamento;
import br.com.rodrigoamora.toolschallenge.service.PagamentoService;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	PagamentoService pagamentoService;
	
	@PostMapping
	public Pagamento realizarPagamento(@RequestBody Pagamento pagamento) {
		return this.pagamentoService.realizarPagamento(pagamento);
	}

	@DeleteMapping
	public void apagarPagamentos() {
		this.pagamentoService.apagarPagamentos();
	}
	
	@GetMapping("/listarTodos")
	public List<Pagamento> listarTodos() {
		return this.pagamentoService.listarTodos();
	}
	
	@GetMapping("/{pagamentoId}")
	public Pagamento buscarPagamentoPorId(@PathVariable(name = "pagamentoId") Long pagamentoId) {
		return this.pagamentoService.buscarPagamentoPorId(pagamentoId);
	}
	
	@PutMapping("/estornar/{pagamentoId}")
	public Pagamento estornarPagamento(@PathVariable(name = "pagamentoId") Long pagamentoId) {
		return this.pagamentoService.estonarPagamento(pagamentoId);
	}
	
}
