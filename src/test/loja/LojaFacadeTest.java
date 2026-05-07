package loja;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class LojaFacadeTest {

    @Test
    void deveFinalizarCompraComSucesso() {

        LojaFacade loja = new LojaFacade();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        loja.finalizarCompra("Notebook", 3000);

        String output = out.toString();

        assertTrue(output.contains("Verificando estoque"));
        assertTrue(output.contains("Processando pagamento"));
        assertTrue(output.contains("Gerando nota fiscal"));
        assertTrue(output.contains("Enviando e-mail"));
        assertTrue(output.contains("Compra finalizada com sucesso"));
    }

    @Test
    void deveVerificarEstoque() {

        EstoqueService estoque = new EstoqueService();

        assertTrue(estoque.verificarEstoque("Mouse"));
    }

    @Test
    void deveProcessarPagamento() {

        PagamentoService pagamento = new PagamentoService();

        assertTrue(pagamento.processarPagamento(100));
    }

    @Test
    void deveGerarNotaFiscal() {

        NotaFiscalService nota = new NotaFiscalService();

        assertDoesNotThrow(() -> nota.gerarNota("Teclado"));
    }

    @Test
    void deveEnviarEmail() {

        EmailService email = new EmailService();

        assertDoesNotThrow(() -> email.enviarConfirmacao("Monitor"));
    }
}