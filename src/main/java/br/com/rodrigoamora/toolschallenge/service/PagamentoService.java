package br.com.rodrigoamora.toolschallenge.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.rodrigoamora.toolschallenge.entity.Pagamento;
import br.com.rodrigoamora.toolschallenge.entity.StatusPagamento;
import br.com.rodrigoamora.toolschallenge.repository.PagamentoRepository;

@Component
public class PagamentoService {

	@Autowired
	PagamentoRepository pagamentoDao;
	
	public Pagamento saveOrEdit(Pagamento pagamento) {
		pagamento.getTransacao().getDescricao().setDataHora(this.formatarDataHora());
		pagamento.getTransacao().getDescricao().setStatus(StatusPagamento.AUTORIZADO);
		
		long codigoAutorizacao = Math.abs(UUID.randomUUID().getMostSignificantBits());
		pagamento.getTransacao().getDescricao().setCodigoAutorizacao(codigoAutorizacao);
		
//		if (pagamento.getTransacao().getFormaPagamento().getTipo() == TipoFormaPagamento.AVISTA) {
//			pagamento.getTransacao().getFormaPagamento().setParcelas(1);
//		}
		
		return this.pagamentoDao.save(pagamento);
	}

	public List<Pagamento> listarTodos() {
		return this.pagamentoDao.findAll();
	}
	
	private String formatarDataHora() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return formatter.format(localDateTime);
	}
}
