package br.com.rodrigoamora.toolschallenge.entity;

public enum StatusPagamento {
	AUTORIZADO("AUTORIZADO"),
	CANCELADO("CANCELADO"),
	NEGADO("NEGADO");
	
	private String status;
	
	private StatusPagamento(String status) {
		this.status = status;
	}
	
}
