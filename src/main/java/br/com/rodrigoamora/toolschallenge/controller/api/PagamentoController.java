package br.com.rodrigoamora.toolschallenge.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigoamora.toolschallenge.entity.Pagamento;
import br.com.rodrigoamora.toolschallenge.service.impl.PagamentoServiceImpl;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	PagamentoServiceImpl pagamentoService;
	
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
	public ResponseEntity<Pagamento> buscarPagamentoPorId(@PathVariable(name = "pagamentoId") Long pagamentoId) {
		Pagamento pagamento = this.pagamentoService.buscarPagamentoPorId(pagamentoId);

		if (pagamento != null) {
			return ResponseEntity.ok(pagamento);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}/estornar")
	public ResponseEntity<Pagamento> estornarPagamento(@PathVariable(name = "id") Long id) {
		Pagamento pagamento = this.pagamentoService.buscarPagamentoPorId(id);
		
		if (pagamento != null) {
			pagamento = this.pagamentoService.estonarPagamento(pagamento);
			return ResponseEntity.ok(pagamento);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/autorizado")
	public Page<Pagamento> buscarPagamentoComStatusAutorizado(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			   										          @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return this.pagamentoService.buscarPagamentoComStatusAutorizado(page, size);
	}
	
	@GetMapping("/cancelado")
	public Page<Pagamento> buscarPagamentoComStatusCancelado(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			   										         @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return this.pagamentoService.buscarPagamentoComStatusCancelado(page, size);
	}

	@GetMapping("/negado")
	public Page<Pagamento> buscarPagamentoComStatusNegado(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			   										      @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return this.pagamentoService.buscarPagamentoComStatusNegado(page, size);
	}
	
}
