package br.com.rodrigoamora.toolschallenge.entity;

public class Descricao {

	private Double valor;
	private String dataHora;
	private String estabelecimento;
	private String nsu;
	private Long codigoAutorizacao;
	private StatusPagamento status;
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getDataHora() {
		return dataHora;
	}
	
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	
	public String getEstabelecimento() {
		return estabelecimento;
	}
	
	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
	public String getNsu() {
		return nsu;
	}
	
	public void setNsu(String nsu) {
		this.nsu = nsu;
	}
	
	public Long getCodigoAutorizacao() {
		return codigoAutorizacao;
	}
	
	public void setCodigoAutorizacao(Long codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}
	
	public StatusPagamento getStatus() {
		return status;
	}
	
	public void setStatus(StatusPagamento status) {
		this.status = status;
	}
	
}
