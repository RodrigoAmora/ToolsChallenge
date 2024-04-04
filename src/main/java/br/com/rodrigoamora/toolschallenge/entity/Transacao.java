package br.com.rodrigoamora.toolschallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "transacoes")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String cartao;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "descricao_id", referencedColumnName = "id")
	private Descricao descricao;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "forma_pagamento_id", referencedColumnName = "id")
	private FormaPagamento formaPagamento;
	
	@OneToOne(mappedBy = "transacao")
    @JsonIgnore
    private Pagamento pagamento;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCartao() {
		return this.adicionarMascaraCartao();
	}
	
	public void setCartao(String cartao) {
		this.cartao = cartao;
	}
	
	public Descricao getDescricao() {
		return descricao;
	}
	
	public void setDescricao(Descricao descricao) {
		this.descricao = descricao;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	private String adicionarMascaraCartao() {
		String cartaoSubSequence = cartao.subSequence(4, 12).toString();
		return cartao.replace(cartaoSubSequence, "********");
	}
}
