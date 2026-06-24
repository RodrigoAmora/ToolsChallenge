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
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
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

}
