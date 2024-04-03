package br.com.rodrigoamora.toolschallenge;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.rodrigoamora.toolschallenge.controller.PagamentoController;
import br.com.rodrigoamora.toolschallenge.entity.Descricao;
import br.com.rodrigoamora.toolschallenge.entity.FormaPagamento;
import br.com.rodrigoamora.toolschallenge.entity.Pagamento;
import br.com.rodrigoamora.toolschallenge.entity.TipoFormaPagamento;
import br.com.rodrigoamora.toolschallenge.entity.Transacao;
import br.com.rodrigoamora.toolschallenge.service.PagamentoService;
import br.com.rodrigoamora.toolschallenge.util.FormatadorDataHora;

@RunWith(SpringRunner.class)
@WebMvcTest(PagamentoController.class)
public class PagamentoApiTest {

	private String baseURL = "http://localhost:8080";
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PagamentoService pagamentoService;
	
	@Test
    public void realizarPagamentoTest() throws Exception{
		//DESCRICAO
        Descricao descricao = new Descricao();
        long codigoAutorizacao = Math.abs(UUID.randomUUID().getMostSignificantBits());
        descricao.setCodigoAutorizacao(codigoAutorizacao);
        
        String padraoDataHora = "dd/MM/yyyy HH:mm:ss";
		String dataHoraFormatada = FormatadorDataHora.formatarDataHora(padraoDataHora);
        descricao.setDataHora(dataHoraFormatada);
        
        descricao.setNsu("1234567890");
        descricao.setValor(10.50);
        
        //FORMA DE PAGAMENTO
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setParcelas(1);
        formaPagamento.setTipo(TipoFormaPagamento.AVISTA);
        
        Transacao transacao = new Transacao();
        transacao.setCartao("1111222233334444");
        transacao.setDescricao(descricao);
        transacao.setFormaPagamento(formaPagamento);
        
        Pagamento pagamento = new Pagamento();
        pagamento.setTransacao(transacao);
        
		mvc.perform(post(baseURL+"/pagamento")
					.content(pagamento.toString())
					.contentType(MediaType.APPLICATION_JSON))
			      	.andExpect(status().isOk())
			      	.andExpect(jsonPath("$[0].transacao.id", is(pagamento.getTransacao().getId())));
    }
}
