package br.com.rodrigoamora.toolschallenge.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import br.com.rodrigoamora.toolschallenge.service.PagamentoService;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;

	@Operation(summary = "Realização do Pagamento")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Realização do Pagmento.", content = @Content(schema = @Schema(implementation = Pagamento.class))),
	})
	@PostMapping
	public Pagamento realizarPagamento(@RequestBody Pagamento pagamento) {
		return this.pagamentoService.realizarPagamento(pagamento);
	}

	@Operation(summary = "Listar Pagamentos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listar todos os pagmentos.", content = @Content(schema = @Schema(implementation = Pagamento.class))),
	})
	@GetMapping
	public Page<Pagamento> listarTodos(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
									   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return this.pagamentoService.listarTodos(page, size);
	}

	@Operation(summary = "Listar Pagamento")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listar pagmento pelo ID.", content = @Content(schema = @Schema(implementation = Pagamento.class))),
	})
	@GetMapping("/{pagamentoId}")
	public ResponseEntity<Pagamento> buscarPagamentoPorId(@PathVariable(name = "pagamentoId") Long pagamentoId) {
		Pagamento pagamento = this.pagamentoService.buscarPagamentoPorId(pagamentoId);

		if (pagamento != null) {
			return ResponseEntity.ok(pagamento);
		}
		
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Estornar do Pagamento")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Estornar pagmento pelo ID.", content = @Content(schema = @Schema(implementation = Pagamento.class))),
	})
	@PutMapping("/{id}/estornar")
	public ResponseEntity<Pagamento> estornarPagamento(@PathVariable(name = "id") Long id) {
		Pagamento pagamento = this.pagamentoService.buscarPagamentoPorId(id);
		
		if (pagamento != null) {
			pagamento = this.pagamentoService.estonarPagamento(pagamento);
			return ResponseEntity.ok(pagamento);
		}
		
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Listar Pagamentos Autorizados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listar todos os pagamentos com status autorizado.", content = @Content(schema = @Schema(implementation = Pagamento.class))),
	})
	@GetMapping("/autorizado")
	public Page<Pagamento> buscarPagamentoComStatusAutorizado(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			   										          @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return this.pagamentoService.buscarPagamentoComStatusAutorizado(page, size);
	}

	@Operation(summary = "Listar Pagamentos Cancelados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listar todos os pagamentos com status cancelado.", content = @Content(schema = @Schema(implementation = Pagamento.class))),
	})
	@GetMapping("/cancelado")
	public Page<Pagamento> buscarPagamentoComStatusCancelado(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			   										         @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return this.pagamentoService.buscarPagamentoComStatusCancelado(page, size);
	}

	@Operation(summary = "Listar Pagamentos Negados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listar todos os pagamentos com status nagado.", content = @Content(schema = @Schema(implementation = Pagamento.class))),
	})
	@GetMapping("/negado")
	public Page<Pagamento> buscarPagamentoComStatusNegado(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			   										      @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return this.pagamentoService.buscarPagamentoComStatusNegado(page, size);
	}
	
}
