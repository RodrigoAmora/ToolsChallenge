package br.com.rodrigoamora.toolschallenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "descricao")
public class Descricao {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
	
	@NotNull
	@Positive
	private Double valor;
	
	@NotNull
	private String dataHora;
	
	@NotNull
	private String estabelecimento;
	
	@NotNull
	private String nsu;
	
	@NotNull
	private Long codigoAutorizacao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
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
