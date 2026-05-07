package loja;

public class LojaFacade {

    private EstoqueService estoqueService;
    private PagamentoService pagamentoService;
    private NotaFiscalService notaFiscalService;
    private EmailService emailService;

    public LojaFacade() {
        this.estoqueService = new EstoqueService();
        this.pagamentoService = new PagamentoService();
        this.notaFiscalService = new NotaFiscalService();
        this.emailService = new EmailService();
    }

    public void finalizarCompra(String produto, double valor) {

        boolean estoqueDisponivel = estoqueService.verificarEstoque(produto);

        if (!estoqueDisponivel) {
            System.out.println("Produto indisponível.");
            return;
        }

        boolean pagamentoAprovado = pagamentoService.processarPagamento(valor);

        if (!pagamentoAprovado) {
            System.out.println("Pagamento recusado.");
            return;
        }

        notaFiscalService.gerarNota(produto);
        emailService.enviarConfirmacao(produto);

        System.out.println("Compra finalizada com sucesso!");
    }
}