package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.cj.protocol.Resultset;

import entidade.Fornecedor;
import entidade.Produto;
import interfaces.IFornecedorRepositorio;

public class FornecedorRepositorio extends DAO implements IFornecedorRepositorio{
	
	
	private ArrayList<Fornecedor> listar(String query){
		Connection con = conectar();
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String cnpj = rs.getString(3);
				String tipoEntrega = rs.getString(4);
				float precoEntrega = rs.getFloat(5);
				String email = rs.getString(6);
				String senha = rs.getString(7);
				String tipo = rs.getString(8);
				float avaliacao = rs.getFloat(9);
								
				fornecedores.add(new Fornecedor(id, nome, cnpj, tipoEntrega, precoEntrega, email, senha, tipo, avaliacao));
			}
			fecharBanco(con);
			return fornecedores;
					
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}

	@Override
	public ArrayList<Fornecedor> listarPorId() {
		String query = "select * from fornecedor order by id";
		return listar(query);
	}

	@Override
	public ArrayList<Fornecedor> listarPorAlfabeto() {
		String query = "select * from fornecedor order by nome";
		return listar(query);
	}

	@Override
	public ArrayList<Fornecedor> listarPorPrecoDeEntrega() {
		String query = "select * from fornecedor order by preco_entrega";
		return listar(query);
	}

	@Override
	public ArrayList<Fornecedor> listarPorTipo() {
		String query = "select * from fornecedor order by tipo";
		return listar(query);
	}

	@Override
	public ArrayList<Fornecedor> listarPorAvaliacao() {
		String query = "select * from fornecedor order by avaliacao desc";
		return listar(query);
	}

	@Override
	public ArrayList<Fornecedor> buscarPorNome(String nomeFornecedor) {
		Connection con = conectar();
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		String query = "select * from fornecedor where nome LIKE '"+nomeFornecedor+"'";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String cnpj = rs.getString(3);
				String tipoEntrega = rs.getString(4);
				float precoEntrega = rs.getFloat(5);
				String email = rs.getString(6);
				String senha = rs.getString(7);
				String tipo = rs.getString(8);
				float avaliacao = rs.getFloat(9);
								
				fornecedores.add(new Fornecedor(id, nome, cnpj, tipoEntrega, precoEntrega, email, senha, tipo, avaliacao));
			}
			fecharBanco(con);
			return fornecedores;
					
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}

	@Override
	public Fornecedor buscarPorId(int idFornecedor) {
		Connection con = conectar();
		Fornecedor fornecedor = null;
		String query = "select * from fornecedor where id LIKE '"+idFornecedor+"'";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String cnpj = rs.getString(3);
				String tipoEntrega = rs.getString(4);
				float precoEntrega = rs.getFloat(5);
				String email = rs.getString(6);
				String senha = rs.getString(7);
				String tipo = rs.getString(8);
				float avaliacao = rs.getFloat(9);
								
				fornecedor = new Fornecedor(id, nome, cnpj, tipoEntrega, precoEntrega, email, senha, tipo, avaliacao);
			}
			fecharBanco(con);
			return fornecedor;
					
		} catch (SQLException e) {
			System.out.println(e);
		}	
		return null;
	}

	@Override
	public Fornecedor validarLogin(String loginEnviada, String senhaEnviada) {
		Connection con = conectar();
		String query = String.format("select * from fornecedor where login LIKE '%s' and senha LIKE '%s'", loginEnviada, senhaEnviada);
		Fornecedor fornecedor = null;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String cnpj = rs.getString(3);
				String tipoEntrega = rs.getString(4);
				float precoEntrega = rs.getFloat(5);
				String email = rs.getString(6);
				String senha = rs.getString(7);
				String tipo = rs.getString(8);
				float avaliacao = rs.getFloat(9);
								
				fornecedor = new Fornecedor(id, nome, cnpj, tipoEntrega, precoEntrega, email, senha, tipo, avaliacao);
			}
			fecharBanco(con);
		} catch (SQLException e) {
			System.out.println(e);
		} 
		
		
		return fornecedor;
	}
	
	@Override
	public ArrayList<String> registrar(String nome, String cnpj, String tipoEntrega, float precoEntrega, String email,
			String senha, String tipo, float avaliacao) {
		ArrayList<String> resposta = new ArrayList<String>();
		Connection con = conectar();
		Fornecedor fornecedor = null;
		String query = String.format("insert into fornecedor value (default, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", nome, cnpj, tipoEntrega, precoEntrega, email, senha, tipo, avaliacao);			
		String queryValida = String.format("select cnpj,login,nome from fornecedor where cnpj LIKE '%s' or login LIKE '%s' or nome like '%s'", cnpj, email, nome);
		String queryValidaPessoa = String.format("select email from pessoa where email like '%s'", email);
			PreparedStatement pst;
			ResultSet rs;
			try {
				pst = con.prepareStatement(queryValida);
				rs = pst.executeQuery();
				
				while (rs.next()) {
					if (rs.getString(1).equals(cnpj)) {
						resposta.add("cnpj");
					}
					if (rs.getString(2).equals(email)) {
						resposta.add("email");
					}
					if (rs.getString(3).equals(nome)) {
						resposta.add("nome");
					}
				}
				
				pst = con.prepareStatement(queryValidaPessoa);
				rs = pst.executeQuery();
				while (rs.next()) {
					if (rs.getString(1).equals(email)) {
						resposta.add("email");
					}
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		
			if (!resposta.isEmpty()) {
				fecharBanco(con);
				return resposta;
			}
		
			try {
				pst = con.prepareStatement(query);
				pst.executeUpdate();
				
				return resposta;
						
			} catch (SQLException e) {
				resposta.add(e.getMessage());
				return resposta;
			}
	}
	
	public boolean validarSenha(String senha, String session) {
		Connection con = conectar();
        String query = String.format("select login_fornecedor.id_fornecedor,fornecedor.senha from login_fornecedor inner join fornecedor on fornecedor.id = login_fornecedor.id_fornecedor where working = 1 and senha like %s and token like '%s';", senha, session);
        
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        fecharBanco(con);
        return false;
	}

	@Override
	public boolean atualizar(int id, String nome, String cnpj, String tipoEntrega, float precoEntrega, String email, String tipo) {
		String query = String.format
		("UPDATE fornecedor SET nome = '%s', cnpj = '%s', tipoEntrega = '%s', preco_entrega = '%s', "
				+ "login = '%s', tipo = '%s' WHERE id = %s",
		nome, cnpj,  tipoEntrega, precoEntrega,  email,  tipo, id);
		
		Connection con = conectar();
		try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();           
            fecharBanco(con);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        fecharBanco(con);
        return false;
	}

	@Override
	public boolean deletar(int id) {
		Connection con = conectar();
        String query = String.format("delete from pessoa where id = %s", id);        
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        fecharBanco(con);
        return false;
	}

	@Override
	public boolean aletarSenha(int id, String novaSenha) {
		Connection con = conectar();
        String query = String.format("UPDATE fornecedor SET senha = '%s' where id = %s", novaSenha, id);        
        try {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        fecharBanco(con);
        return false;
	}

	@Override
	public ArrayList<Produto> listarCardapio(int id_Fornecedor) {
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		
		Connection con = conectar();
		String query = String.format("select * from produto where id_fornecedor = %s", id_Fornecedor);
		
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery(); 
			
	        int id;
	        int idFornecedor;
	        String nome;
	        String descricao;
	        float desconto;
	        float preco;
	        String tipo;
	        int estoque;
	        int curtidas;
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
			return produtos;
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		
		
		
		return null;
	}



}
