package br.com.rodrigoamora.toolschallenge.entity;

public class FormaPagamento {

	private TipoFormaPagamento formaPagamento;
	private Integer parcelas;
	
	public TipoFormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public void setFormaPagamento(TipoFormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public Integer getParcelas() {
		return parcelas;
	}
	
	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}
	
}
