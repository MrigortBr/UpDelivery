package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.Venda;
import interfaces.IVendaRepositorio;

public class VendaRepositorio extends DAO implements IVendaRepositorio{
    public ArrayList<Venda> buscarHistorico(int idUser) {
        ArrayList<Venda> vendas = new ArrayList<Venda>();
        
        Connection con = conectar();
        
        String query = String.format("select * from venda where id_usuario = %s", idUser);
        
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            String dataCompra;
            int idComprador;
            int idProduto;
            int idFornecedor;
            float preco;
            float desconto;
            float precoFinal;
            String tipoPagamento;
            String cartao;
            String estado;
            
            while (rs.next()) {
                dataCompra = rs.getString(1);
                idComprador = rs.getInt(2);
                idProduto = rs.getInt(3);
                idFornecedor = rs.getInt(4);
                preco = rs.getFloat(5);
                desconto = rs.getFloat(6);
                precoFinal = rs.getFloat(7);
                tipoPagamento = rs.getString(8);
                cartao = rs.getString(9);
                estado = rs.getString(10);
                Venda venda = new Venda(dataCompra, idComprador, idProduto, idFornecedor, preco, desconto, precoFinal, tipoPagamento, cartao, estado);
                vendas.add(venda);
               }
            fecharBanco(con);
        } catch (SQLException e) {
            e.printStackTrace();
            fecharBanco(con);

        }
        return vendas;
    }

	@Override
	public boolean registrar(String dataAtual, int idUsuario, int idProduto, int idFornecedor, float preco, float desconto, float precoFinal, int quantidade, String tipoPagamento, String cartao, String estado) {
        Connection con = conectar();
        String query = String.format("insert into venda values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')", dataAtual, idUsuario, idProduto, idFornecedor, preco , desconto, precoFinal,quantidade, tipoPagamento, cartao, estado);
        
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();
            fecharBanco(con);
            System.out.println("Olá");
            return true;
        } catch (SQLException e) {
        	System.out.println(e);
            fecharBanco(con);
    		return false;

        } 
        
	}
}
