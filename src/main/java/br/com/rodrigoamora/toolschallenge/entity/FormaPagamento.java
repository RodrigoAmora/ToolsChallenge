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
@Table(name = "forma_pagamento")
public class FormaPagamento {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoFormaPagamento formaPagamento;
	
	@NotNull
	@Positive
	private Integer parcelas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
