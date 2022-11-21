package interfaces;

import java.util.ArrayList;

import entidade.Venda;

public interface IVendaRepositorio {
    public ArrayList<Venda> buscarHistorico(int idUser);
    public boolean registrar(String dataAtual,int idUsuario,int idProduto,int idFornecedor,float preco, float desconto,float precoFinal,  int quantidade, String  tipoPagamento,String  cartao,String  estado);
}
