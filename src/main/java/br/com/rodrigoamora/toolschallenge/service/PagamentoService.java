package br.com.rodrigoamora.toolschallenge.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import br.com.rodrigoamora.toolschallenge.entity.Pagamento;
import br.com.rodrigoamora.toolschallenge.entity.StatusPagamento;
import br.com.rodrigoamora.toolschallenge.entity.TipoFormaPagamento;
import br.com.rodrigoamora.toolschallenge.exception.BusinessValidationException;
import br.com.rodrigoamora.toolschallenge.repository.PagamentoRepository;
import br.com.rodrigoamora.toolschallenge.util.FormatadorDataHora;
import br.com.rodrigoamora.toolschallenge.util.validator.CartaoValidator;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

	private PagamentoRepository pagamentoRepository;
	
	public Pagamento realizarPagamento(Pagamento pagamento) {
		String padraoDataHora = "dd/MM/yyyy HH:mm:ss";
		String dataHoraFormatada = FormatadorDataHora.formatarDataHora(padraoDataHora);
		
		pagamento.getTransacao().getDescricao().setDataHora(dataHoraFormatada);
		
		this.verificarParcelasComTipoDePagamento(pagamento);
		
		long codigoAutorizacao = Math.abs(UUID.randomUUID().getMostSignificantBits());
		pagamento.getTransacao().getDescricao().setCodigoAutorizacao(codigoAutorizacao);
		
		if (verificarCartaoValido(pagamento.getTransacao().getCartao())) {
			pagamento.getTransacao().getDescricao().setStatus(StatusPagamento.AUTORIZADO);
		} else {
			pagamento.getTransacao().getDescricao().setStatus(StatusPagamento.NEGADO);
		}
		
		pagamento.getTransacao().adicionarMascaraCartao();
		
		return this.pagamentoRepository.save(pagamento);
	}

	public Page<Pagamento> listarTodos(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return this.pagamentoRepository.findAll(pageRequest);
    }
	
	public Pagamento buscarPagamentoPorId(Long pagamentoId) {
		return this.pagamentoRepository.findById(pagamentoId).get();
	}
	
	public Pagamento estonarPagamento(Pagamento pagamento) {
		pagamento.getTransacao().getDescricao().setStatus(StatusPagamento.CANCELADO);
		return this.pagamentoRepository.save(pagamento);
	}
	
	private Boolean verificarParcelasComTipoDePagamento(Pagamento pagamento) {
		TipoFormaPagamento tipoFormaPagamento = pagamento.getTransacao().getFormaPagamento().getTipo();
		Integer parcelas = pagamento.getTransacao().getFormaPagamento().getParcelas();
		
		if (tipoFormaPagamento == TipoFormaPagamento.AVISTA && parcelas > 1) {
			throw new BusinessValidationException("Tipo de pagamento a vista nao pode ter mais que 1 parcela.");
		}
		
		if ((tipoFormaPagamento == TipoFormaPagamento.PARCELADO_EMISSOR || tipoFormaPagamento == TipoFormaPagamento.PARCELADO_LOJA)
				&& parcelas < 2 ) {
			throw new BusinessValidationException("Tipo de pagamento parcelado precisa ter no minimo 2 parcelas.");
		}
		
		return true;
	}

	public Page<Pagamento> buscarPagamentoComStatusAutorizado(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");
		return this.pagamentoRepository.buscarPagamentoComStatusAutorizado(pageRequest);
	}

	public Page<Pagamento> buscarPagamentoComStatusCancelado(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");
		return this.pagamentoRepository.buscarPagamentoComStatusCancelado(pageRequest);
	}

	public Page<Pagamento> buscarPagamentoComStatusNegado(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");
		return this.pagamentoRepository.buscarPagamentoComStatusNegado(pageRequest);
	}

	private boolean verificarCartaoValido(String cartao) {
		return CartaoValidator.validate(cartao.replaceAll(" ", ""));
	}

}
