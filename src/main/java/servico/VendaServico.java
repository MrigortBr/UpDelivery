package servico;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import entidade.Venda;
import interfaces.IVendaRepositorio;
import interfaces.IVendaServico;
import model.VendaRepositorio;

public class VendaServico implements IVendaServico{
    IVendaRepositorio repositorio = new VendaRepositorio();
    
    
    public ArrayList<Venda> buscarHistoricoUsuario(int id){
        return repositorio.buscarHistorico(id);
    }


	@Override
	public boolean registrar(int idUsuario,int idProduto,int idFornecedor,float preco, float desconto,float precoFinal, int quantidade, String  tipoPagamento,String  cartao,String  estado ) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dataAtual = formatter.format(Calendar.getInstance().getTime());
		return repositorio.registrar(dataAtual, idUsuario, idProduto, idFornecedor, preco, desconto, precoFinal, quantidade, tipoPagamento, cartao, estado);
	}
    
}

