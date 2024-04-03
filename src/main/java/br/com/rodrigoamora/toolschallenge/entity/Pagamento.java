package br.com.rodrigoamora.toolschallenge.entity;

public class Pagamento {

	private Long id;
	private String cartao;
	private Descricao descricao;
	private FormaPagamento formaPagamento;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCartao() {
		return cartao;
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
	
}
