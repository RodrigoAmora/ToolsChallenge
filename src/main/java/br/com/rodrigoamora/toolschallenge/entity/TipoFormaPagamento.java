package br.com.rodrigoamora.toolschallenge.entity;

public enum TipoFormaPagamento {
	AVISTA("AVISTA"),
	PARCELADO_LOJA("PARCELADO_LOJA"),
	PARCELADO_EMISSOR("PARCELADO_EMISSOR");
	
	private String tipo;
	
	private TipoFormaPagamento(String tipo) {
		this.tipo = tipo;
	}
	
}
