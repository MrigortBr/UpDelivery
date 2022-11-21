package interfaces;

import java.util.ArrayList;

import entidade.Produto;

public interface IProdutoRepositorio {
    public ArrayList<Produto> buscarDescontosProdutos();
    public ArrayList<Produto> buscarProdutosPorNome(String nomeProduto);
    public Produto buscarProdutosPorId(int idProduto);
    public boolean registrar(int idFornecedor, String nome,String descricao, String preco, String desconto, String tipo);
    public boolean atualizar(int idFornecedor, int idProduto,String nome,String descricao, String preco, String desconto, String tipo);
    public boolean deletar(int idFornecedor, int idProduto);

}
