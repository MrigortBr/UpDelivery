package interfaces;

import java.util.ArrayList;

import entidade.Venda;

public interface IVendaServico {
    public ArrayList<Venda> buscarHistoricoUsuario(int id);
    public boolean registrar(int idUsuario,int idProduto,int idFornecedor,float preco, float desconto,float precoFinal, int quantidade, String  tipoPagamento,String  cartao,String  estado);
}
