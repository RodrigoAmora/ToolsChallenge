package br.com.rodrigoamora.toolschallenge.service.impl;

import br.com.rodrigoamora.toolschallenge.entity.*;
import br.com.rodrigoamora.toolschallenge.repository.PagamentoRepository;
import br.com.rodrigoamora.toolschallenge.util.FormatadorDataHora;
import br.com.rodrigoamora.toolschallenge.util.validator.CartaoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class PagamentoServiceImplTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private PagamentoServiceImpl pagamentoService;

    private Pagamento pagamentoMock;

    @BeforeEach
    void init() {
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setTipo(TipoFormaPagamento.AVISTA);
        formaPagamento.setParcelas(1);

        Descricao descricao = new Descricao();
        descricao.setDataHora(FormatadorDataHora.formatarDataHora("dd/MM/yyyy HH:mm:ss"));
        descricao.setCodigoAutorizacao(111L);
        descricao.setEstabelecimento("Starbucks");
        descricao.setNsu("1234567890");
        descricao.setValor(100D);

        Transacao transacao = new Transacao();
        transacao.setCartao("5456 4872 8195 2983");
        transacao.setFormaPagamento(formaPagamento);
        transacao.setDescricao(descricao);

        pagamentoMock = new Pagamento();
        pagamentoMock.setTransacao(transacao);

    }

    @Test
    void testRealizarPagamentoComCartaoValido() {
        when(pagamentoRepository.save(any(Pagamento.class))).thenAnswer(invocation -> {
            Pagamento pagamento = invocation.getArgument(0);
            pagamento.setId(1L); // Setando o ID para simular persistência.
            return pagamento;
        });
        mockStaticMethods(true); // Simulando cartão válido.

        Pagamento pagamentoRealizado = pagamentoService.realizarPagamento(pagamentoMock);

        assertNotNull(pagamentoRealizado);
        assertNotNull(pagamentoRealizado.getId());
        assertNotNull(pagamentoRealizado.getTransacao().getDescricao().getDataHora());
        assertEquals(StatusPagamento.AUTORIZADO, pagamentoRealizado.getTransacao().getDescricao().getStatus());
        assertNotNull(pagamentoRealizado.getTransacao().getDescricao().getCodigoAutorizacao());
        assertTrue(pagamentoRealizado.getTransacao().getCartao().contains("********"));

        // Verificando interação com o mock.
        verify(pagamentoRepository, times(1)).save(pagamentoMock);
    }

    @Test
    void testRealizarPagamentoComCartaoInvalido() {
        pagamentoMock.getTransacao().setCartao("1234 4872 8195 2983");

        when(pagamentoRepository.save(any(Pagamento.class))).thenAnswer(invocation -> {
            Pagamento pagamento = invocation.getArgument(0);
            pagamento.setId(1L); // Setando o ID para simular persistência.
            return pagamento;
        });
        mockStaticMethods(false); // Simulando cartão inválido.

        Pagamento pagamentoRealizado = pagamentoService.realizarPagamento(pagamentoMock);


        assertNotNull(pagamentoRealizado);
        assertNotNull(pagamentoRealizado.getId());
        assertNotNull(pagamentoRealizado.getTransacao().getDescricao().getDataHora());
        assertEquals(StatusPagamento.NEGADO, pagamentoRealizado.getTransacao().getDescricao().getStatus());
        assertNotNull(pagamentoRealizado.getTransacao().getDescricao().getCodigoAutorizacao());
        assertTrue(pagamentoRealizado.getTransacao().getCartao().contains("********"));


        verify(pagamentoRepository, times(1)).save(pagamentoMock);
    }

    private void mockStaticMethods(boolean isValidCard) {
        try (var mockedUUID = mockStatic(UUID.class); var mockedValidator = mockStatic(CartaoValidator.class)) {
            mockedValidator.when(() -> CartaoValidator.validate(anyString()))
                    .thenReturn(isValidCard);
        }
    }

}
