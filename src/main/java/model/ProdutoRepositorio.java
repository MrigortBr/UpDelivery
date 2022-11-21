package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import entidade.Produto;
import interfaces.IProdutoRepositorio;

public class ProdutoRepositorio extends DAO implements IProdutoRepositorio{

    private ArrayList<Produto> buscarProdutoLista(String query){
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        Connection con = conectar();
        ResultSet rs = null;
        try {
            PreparedStatement pst = con.prepareStatement(query);
            
            rs = pst.executeQuery();
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }
        
        int id;
        int idFornecedor;
        String nome;
        String descricao;
        float desconto;
        float preco;
        String tipo;
        int estoque;
        int curtidas;
        
        try {
            while (rs.next()) {
                id = rs.getInt(1);
                idFornecedor = rs.getInt(2);
                nome = rs.getString(3);
                descricao = rs.getString(4);
                preco = rs.getFloat(5);
                desconto = rs.getFloat(6);
                tipo = rs.getString(7);
                estoque = rs.getInt(8);
                curtidas = rs.getInt(9);
                Produto p = new Produto(id, idFornecedor, nome, descricao, preco, desconto, tipo, estoque, curtidas);
                produtos.add(p);
           }
            fecharBanco(con);

            return produtos;
            
        }catch (Exception e) {
            fecharBanco(con);
            return null;
        }
    }
     
    private Produto buscarProdutoEntidade(String query) {
        Produto produto = null;
        Connection con = conectar();
        ResultSet rs = null;
        try {
            PreparedStatement pst = con.prepareStatement(query);
            
            rs = pst.executeQuery();
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }
        
        int id;
        int idFornecedor;
        String nome;
        String descricao;
        float desconto;
        float preco;
        String tipo;
        int estoque;
        int curtidas;
        
        try {
            while (rs.next()) {
                id = rs.getInt(1);
                idFornecedor = rs.getInt(2);
                nome = rs.getString(3);
                descricao = rs.getString(4);
                preco = rs.getFloat(5);
                desconto = rs.getFloat(6);
                tipo = rs.getString(7);
                estoque = rs.getInt(8);
                curtidas = rs.getInt(9);
                produto = new Produto(id, idFornecedor, nome, descricao, preco, desconto, tipo, estoque, curtidas);
           }
            con.close();

         
            return produto;
            
        }catch (Exception e) {
            fecharBanco(con);
            return null;
        }
    }
    
    public ArrayList<Produto> buscarDescontosProdutos() {
        String query = "select * from produto where desconto > 0 order by desconto desc";      
        return buscarProdutoLista(query);
    }
    
    public ArrayList<Produto> buscarProdutosPorNome(String nomeProduto){
        String query = ("select * from produto where nome LIKE '%"+nomeProduto+"%'");
        return buscarProdutoLista(query);
    }
    
    public Produto buscarProdutosPorId(int idProduto){
        String query = String.format("select * from produto where id LIKE %s", idProduto);
        return buscarProdutoEntidade(query);
    }

	@Override
	public boolean registrar(int idFornecedor, String nome, String descricao, String preco, String desconto,String tipo) {
		Connection con = conectar();
		String query = String.format("INSERT INTO produto VALUES (default, '%s', '%s', '%s', '%s', '%s', '%s', -1, 0)", idFornecedor, nome, descricao,  preco, desconto, tipo);
		System.out.println(query);

		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.execute(query);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}	
		return false;
	}

	@Override
	public boolean atualizar(int idFornecedor, int idProduto, String nome, String descricao, String preco,String desconto, String tipo) {
		Connection con = conectar();
		String query = String.format("UPDATE produto SET nome = '%s', descricao = '%s', preco = '%s', desconto = '%s', tipo = '%s' WHERE id = '%s' and id_fornecedor = '%s'", nome, descricao,  preco, desconto, tipo, idProduto, idFornecedor);
		System.out.println(query);
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.execute(query);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}	
		return false;
	}

	@Override
	public boolean deletar(int idFornecedor, int idProduto) {
		Connection con = conectar();
		String query = String.format("DELETE FROM produto WHERE id = '%s' and id_fornecedor = '%s'", idProduto, idFornecedor);

		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.execute(query);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}	
		return false;
	}
}
