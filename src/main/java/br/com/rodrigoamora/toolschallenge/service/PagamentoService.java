package br.com.rodrigoamora.toolschallenge.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.rodrigoamora.toolschallenge.entity.Pagamento;
import br.com.rodrigoamora.toolschallenge.entity.StatusPagamento;
import br.com.rodrigoamora.toolschallenge.entity.TipoFormaPagamento;
import br.com.rodrigoamora.toolschallenge.exception.BusinessValidationException;
import br.com.rodrigoamora.toolschallenge.repository.PagamentoRepository;
import br.com.rodrigoamora.toolschallenge.util.FormatadorDataHora;

@Component
public class PagamentoService {

	@Autowired
	PagamentoRepository pagamentoDao;
	
	public Pagamento saveOrEdit(Pagamento pagamento) {
		String padraoDataHora = "dd/MM/yyyy HH:mm:ss";
		String dataHoraFormatada = FormatadorDataHora.formatarDataHora(padraoDataHora);
		
		pagamento.getTransacao().getDescricao().setDataHora(dataHoraFormatada);
		
		TipoFormaPagamento tipoFormaPagamento = pagamento.getTransacao().getFormaPagamento().getTipo();
		Integer parcelas = pagamento.getTransacao().getFormaPagamento().getParcelas();
		
		if (tipoFormaPagamento == TipoFormaPagamento.AVISTA && parcelas > 1) {
			throw new BusinessValidationException("Tipo de pagamento a vista nao pode ter mais que 1 parcela.");
		}
		
		if ((tipoFormaPagamento == TipoFormaPagamento.PARCELADO_EMISSOR || tipoFormaPagamento == TipoFormaPagamento.PARCELADO_LOJA)
				&& parcelas < 2 ) {
			throw new BusinessValidationException("Tipo de pagamento parcelado precisa ter no minimo 2 parcelas.");
		}
		
		long codigoAutorizacao = Math.abs(UUID.randomUUID().getMostSignificantBits());
		pagamento.getTransacao().getDescricao().setCodigoAutorizacao(codigoAutorizacao);
		pagamento.getTransacao().getDescricao().setStatus(StatusPagamento.AUTORIZADO);
		
		return this.pagamentoDao.save(pagamento);
	}

	public List<Pagamento> listarTodos() {
		return this.pagamentoDao.findAll();
	}
	
	public Pagamento buscarPagamentoPorId(Long pagamentoId) {
		return this.pagamentoDao.findById(pagamentoId).get();
	}
	
	public Pagamento estonerPagamento(Long pagamentoId) {
		Pagamento pagamento = this.pagamentoDao.findById(pagamentoId).get();
		pagamento.getTransacao().getDescricao().setStatus(StatusPagamento.CANCELADO);
		return this.pagamentoDao.save(pagamento);
	}
	
}
