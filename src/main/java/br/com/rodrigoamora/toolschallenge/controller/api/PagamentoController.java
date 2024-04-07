package br.com.rodrigoamora.toolschallenge.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping
	public Page<Pagamento> listarTodos(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
									   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return this.pagamentoService.listarTodos(page, size);
	}
	
	@GetMapping("/{pagamentoId}")
	public Pagamento buscarPagamentoPorId(@PathVariable(name = "pagamentoId") Long pagamentoId) {
		return this.pagamentoService.estonarPagamento(pagamentoId);
	}
	
	@PutMapping("/{id}/estornar")
	public Pagamento estornarPagamento(@PathVariable(name = "id") Long id) {
		return this.pagamentoService.estonarPagamento(id);
	}
	
}
