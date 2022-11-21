package entidade;

public class Venda {
    private String dataCompra;
    private int idComprador;
    private int idProduto;
    private int idFornecedor;
    private float preco;
    private float desconto;
    private float precoFinal;
    private String tipoPagamento;
    private String cartao;
    private String estado;

    
    public Venda(String dataCompra, int idComprador, int idProduto, int idFornecedor, float preco, float desconto,
            float precoFinal, String tipoPagamento, String cartao, String estado) {
        super();
        this.dataCompra = dataCompra;
        this.idComprador = idComprador;
        this.idProduto = idProduto;
        this.idFornecedor = idFornecedor;
        this.preco = preco;
        this.desconto = desconto;
        this.precoFinal = precoFinal;
        this.tipoPagamento = tipoPagamento;
        this.cartao = cartao;
        this.setEstado(estado);
    }
    public String getDataCompra() {
        return dataCompra;
    }
    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }
    public int getIdComprador() {
        return idComprador;
    }
    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public int getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }
    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }
    public float getDesconto() {
        return desconto;
    }
    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }
    public float getPrecoFinal() {
        return precoFinal;
    }
    public void setPrecoFinal(float precoFinal) {
        this.precoFinal = precoFinal;
    }
    public String getTipoPagamento() {
        return tipoPagamento;
    }
    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    public String getCartao() {
        return cartao;
    }
    public void setCartao(String cartao) {
        this.cartao = cartao;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
