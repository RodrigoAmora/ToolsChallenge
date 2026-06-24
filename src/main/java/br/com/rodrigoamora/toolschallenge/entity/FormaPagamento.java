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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "forma_pagamento")
public class FormaPagamento {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@JsonIgnore
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoFormaPagamento tipo;
	
	@NotNull
	@Positive
	private Integer parcelas;
	
	@OneToOne(mappedBy = "formaPagamento")
    @JsonIgnore
    private Transacao transacao;

}
