package br.com.rodrigoamora.toolschallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "descricao")
public class Descricao {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@JsonIgnore
    private Long id;
	
	@NotBlank
	@Positive
	private Double valor;
	
	private String dataHora;
	
	@NotBlank
	private String estabelecimento;
	
	@NotBlank
	private String nsu;
	
	private Long codigoAutorizacao;
	
	@Enumerated(EnumType.STRING)
	private StatusPagamento status;
	
    @OneToOne(mappedBy = "descricao")
    @JsonIgnore
    private Transacao transacao;
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
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

	public Transacao getTransacao() {
		return transacao;
	}

	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}
	
}
