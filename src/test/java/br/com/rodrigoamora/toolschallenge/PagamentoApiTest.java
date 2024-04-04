package br.com.rodrigoamora.toolschallenge;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rodrigoamora.toolschallenge.config.WebSecurityConfig;
import br.com.rodrigoamora.toolschallenge.controller.PagamentoController;
import br.com.rodrigoamora.toolschallenge.entity.Descricao;
import br.com.rodrigoamora.toolschallenge.entity.FormaPagamento;
import br.com.rodrigoamora.toolschallenge.entity.Pagamento;
import br.com.rodrigoamora.toolschallenge.entity.TipoFormaPagamento;
import br.com.rodrigoamora.toolschallenge.entity.Transacao;
import br.com.rodrigoamora.toolschallenge.service.PagamentoService;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
//@RunWith(JUnit4.class)
@Import(WebSecurityConfig.class)
@WebMvcTest(PagamentoController.class)
@ActiveProfiles("test")
public class PagamentoApiTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private PagamentoService pagamentoService;
	
	@Test
    public void realizarPagamentoTest() throws Exception {
		//DESCRICAO
        Descricao descricao = new Descricao();
        long codigoAutorizacao = Math.abs(UUID.randomUUID().getMostSignificantBits());
        descricao.setCodigoAutorizacao(codigoAutorizacao);
        
        descricao.setEstabelecimento("Starbucks");
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
        
        
        String a = this.objectMapper.writeValueAsString(pagamento);
        
        given()
        .contentType(ContentType.JSON)
        .body(a)
        .post("/pagamento")
        .then()
        .body("transacao.id", notNullValue())
        .statusCode(200);
    }
	
//	@Test
//    public void realizarPagamentoTestWithMockMvc() throws Exception {
//		//DESCRICAO
//        Descricao descricao = new Descricao();
//        long codigoAutorizacao = Math.abs(UUID.randomUUID().getMostSignificantBits());
//        descricao.setCodigoAutorizacao(codigoAutorizacao);
//        
//        descricao.setEstabelecimento("Starbucks");
//        descricao.setNsu("1234567890");
//        descricao.setValor(10.50);
//        
//        //FORMA DE PAGAMENTO
//        FormaPagamento formaPagamento = new FormaPagamento();
//        formaPagamento.setParcelas(1);
//        formaPagamento.setTipo(TipoFormaPagamento.AVISTA);
//        
//        Transacao transacao = new Transacao();
//        transacao.setCartao("1111222233334444");
//        transacao.setDescricao(descricao);
//        transacao.setFormaPagamento(formaPagamento);
//        
//        Pagamento pagamento = new Pagamento();
//        pagamento.setId(1L);
//        pagamento.setTransacao(transacao);
//        
//        String a = this.objectMapper.writeValueAsString(pagamento);
//        
//        String endpointPagamento = "http://localhost:8080/pagamento";
//        
//		mvc.perform(post(endpointPagamento)//.with(digest().)
//					.contentType(MediaType.APPLICATION_JSON)
//					.content(a))
//			      	.andExpect(status().isOk())
//			      	.andExpect(jsonPath("$.transacao").value(1));
//	}

}
