package br.com.rodrigoamora.toolschallenge.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rodrigoamora.toolschallenge.config.WebSecurityConfig;
import br.com.rodrigoamora.toolschallenge.entity.Descricao;
import br.com.rodrigoamora.toolschallenge.entity.FormaPagamento;
import br.com.rodrigoamora.toolschallenge.entity.Pagamento;
import br.com.rodrigoamora.toolschallenge.entity.TipoFormaPagamento;
import br.com.rodrigoamora.toolschallenge.entity.Transacao;
import br.com.rodrigoamora.toolschallenge.service.PagamentoService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


@Import(WebSecurityConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureJsonTesters
public class PagamentoApiTest {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private PagamentoService pagamentoService;
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8080";
	}
	
	@Test
    public void realizarPagamentoTest() throws Exception {
		Pagamento pagamento = this.instanciarPagamento();
        var pagamentoJson = this.pagamentoToJson(pagamento);
        
        given()
        .contentType(ContentType.JSON)
        .body(pagamentoJson)
        .post("/pagamento")
        .then()
        .body("transacao.id", notNullValue())
        .statusCode(200);
    }
	
	@Test
    public void estornarPagamentoTest() throws Exception {
		Pagamento pagamento = this.instanciarPagamento();
        var pagamentoJson = this.pagamentoToJson(pagamento);
        
        Response response = given()
        .contentType(ContentType.JSON)
        .body(pagamentoJson)
        .when()
        .post("/pagamento");
        
        String responseBody = response.getBody().asString();
        Pagamento pagamentoResponse = this.jsonToPagamento(responseBody);
        
        var pagamentoResponseId = Integer.parseInt(pagamentoResponse.getTransacao().getId().toString());
        
        given()
        .contentType(ContentType.JSON)
        .body(pagamentoJson)
        .put("/pagamento/estornar/"+pagamentoResponseId)
        .then()
        .body("transacao.id", equalTo(pagamentoResponseId))
        .body("transacao.descricao.status", equalTo("CANCELADO"))
        .statusCode(200);
    }
	
	@Test
    public void listarTodosPagamentosTest() throws Exception {
		Pagamento pagamento = this.instanciarPagamento();
        var pagamentoJson = this.pagamentoToJson(pagamento);
        
        given()
        .contentType(ContentType.JSON)
        .body(pagamentoJson)
        .when()
        .post("/pagamento");
        
        
        given()
        .contentType(ContentType.JSON)
        .get("/pagamento/listarTodos")
        .then()
        .statusCode(200);
    }
	
	@Test
    public void buscarPagamentoPorId() throws Exception {
        Pagamento pagamento = this.instanciarPagamento();
        var pagamentoJson = this.pagamentoToJson(pagamento);
        
        Response response = given()
        .contentType(ContentType.JSON)
        .body(pagamentoJson)
        .when()
        .post("/pagamento");
        
        String responseBody = response.getBody().asString();
        Pagamento pagamentoResponse = this.jsonToPagamento(responseBody);
        
        var pagamentoResponseId = Integer.parseInt(pagamentoResponse.getTransacao().getId().toString());
        
        given()
        .contentType(ContentType.JSON)
        .when()
        .get("/pagamento/"+pagamentoResponseId)
        .then()
        .body("transacao.id", equalTo(pagamentoResponseId))
        .statusCode(200);
    }
	
	private Pagamento instanciarPagamento() {
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
        
        return pagamento;
	}
	
	private String pagamentoToJson(Pagamento pagamento) throws JsonMappingException, JsonProcessingException {
		return this.objectMapper.writeValueAsString(pagamento);
	}
	
	private Pagamento jsonToPagamento(String pagamentoResponseBody) throws JsonMappingException, JsonProcessingException {
		return this.objectMapper.readValue(pagamentoResponseBody, Pagamento.class);
	}
	
//	@Autowired
//	private MockMvc mvc;
	
//	@Test
//	@WithMockUser
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
//        transacao.setId(1L);
//        transacao.setCartao("1111222233334444");
//        transacao.setDescricao(descricao);
//        transacao.setFormaPagamento(formaPagamento);
//        
//        Pagamento pagamento = new Pagamento();
//        pagamento.setTransacao(transacao);
//        
//        
//        var pagamentoJson = this.objectMapper.writeValueAsString(pagamento);
//        
//        when(this.pagamentoService.realizarPagamento(any())).thenReturn(pagamento);
//        
//        mvc.perform(post("/pagamento")
//    			.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
//				.content(pagamentoJson))
//        		.andDo(print())
//        		.andExpect(MockMvcResultMatchers.jsonPath("$.transacao.id").value(1));
//        
////		      	.andExpect(status().isOk())
////		      	.andExpect(jsonPath("$.transacao.id").value(1));
//        
//	}

}
