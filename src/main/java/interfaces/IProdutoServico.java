package interfaces;

import java.util.ArrayList;
import java.util.Map;

import entidade.Fornecedor;
import entidade.Produto;

public interface IProdutoServico {
    public ArrayList<Produto> buscarDescontos();
    public ArrayList<Produto> buscarProdutoPorNome(String nome);
    public ArrayList<Produto> buscarPorTipo(String tipo);
    public Produto buscarProdutoPorId(int id);
    public Map<String, ArrayList<Produto>> dividirProdutosPorCategoria (ArrayList<Produto> produtos);
    public boolean registrar(int idFornecedor, String nome,String descricao, String preco, String desconto, String tipo);
    public boolean atualizar(int idFornecedor, int idProduto,String nome,String descricao, String preco, String desconto, String tipo);
    public boolean deletar(int idFornecedor, int idProduto);
    public Map<Fornecedor, Map<Produto, Integer>> dividirPorFonecedor(Map<Produto, Integer> map);
    public Map<Produto, Integer> apagarFornecedor(int idFornecedor, Map<Produto, Integer> map);
}
