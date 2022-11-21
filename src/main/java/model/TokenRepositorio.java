package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidade.Token;
import interfaces.ITokenRepositorio;

public class TokenRepositorio extends DAO implements ITokenRepositorio{
	
	@Override
	public boolean adicionarToken(Token token) {
		Connection con = conectar();
		String query = String.format("insert into login_usuario values('%s', '%s', '%s', '%s', '%s', %s)", token.getUsuario().getId(), token.getToken(), token.getTokenSecundario(), token.getDataCriacao(), token.getDataValidade(), token.getWorking()); 
		String querySecundario = String.format("UPDATE login_usuario SET working = false where id_usuario = %s", token.getUsuario().getId());
		try {
		    
			PreparedStatement pst = con.prepareStatement(querySecundario);
			pst.executeUpdate();
			pst = con.prepareStatement(query);
			pst.executeUpdate();
            fecharBanco(con);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
	        fecharBanco(con);
			return false;
		}
	}
	
	@Override
	public Token autenticarToken(String token) {
		if (token.isEmpty() == false) {
			Connection con = conectar();
			int idUsuario = 0;
			String tk = "";
			String tkSecundario = "";
			String dataCriacao = "";
			String dataValidade = "";
			Boolean working = null;
		
			Token tokenNew = null;
			String query = String.format("select * from login_usuario where token like('%s') and working = true", token);
			try {
				PreparedStatement pst = con.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					idUsuario = rs.getInt(1);
					tk = rs.getString(2);
					tkSecundario = rs.getString(3);
					dataCriacao = rs.getString(4);
					dataValidade = rs.getString(5);
					working = rs.getBoolean(6);
					tokenNew = new Token(idUsuario, tk, tkSecundario, dataCriacao, dataValidade, working);

				}
	            fecharBanco(con);
				if (tokenNew == null) {	    
					return null;
				}else {
					return tokenNew;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
		
	}

	@Override
	public void desativarToken(int id) {
		Connection con = conectar();
		String query = String.format("update login_usuario set working = %s where id_usuario = %s", false, id); 
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.executeUpdate();
            fecharBanco(con);
		} catch (SQLException e) {
			e.printStackTrace();
			fecharBanco(con);
		}
	}

	@Override
	public boolean adicionarTokenFornecedor(Token token) {
		Connection con = conectar();
		String query = String.format("insert into login_fornecedor values('%s', '%s', '%s', '%s', '%s', %s)", token.getFornecedor().getId(), token.getToken(), token.getTokenSecundario(), token.getDataCriacao(), token.getDataValidade(), token.getWorking()); 
		String querySecundario = String.format("UPDATE login_fornecedor SET working = false where id_fornecedor = %s", token.getFornecedor().getId());
		try {
		    
			PreparedStatement pst = con.prepareStatement(querySecundario);
			pst.executeUpdate();
			pst = con.prepareStatement(query);
			pst.executeUpdate();
            fecharBanco(con);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
	        fecharBanco(con);
			return false;
		}
	}

	@Override
	public Token autenticarTokenFornecedor(String token) {
		if (token.isEmpty() == false) {
			Connection con = conectar();
			int idUsuario = 0;
			String tk = "";
			String tkSecundario = "";
			String dataCriacao = "";
			String dataValidade = "";
			Boolean working = null;
		
			Token tokenNew = null;
			String query = String.format("select * from login_fornecedor where token like('%s') and working = true", token);
			try {
				PreparedStatement pst = con.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					idUsuario = rs.getInt(1);
					tk = rs.getString(2);
					tkSecundario = rs.getString(3);
					dataCriacao = rs.getString(4);
					dataValidade = rs.getString(5);
					working = rs.getBoolean(6);
					tokenNew = new Token(idUsuario, tk, tkSecundario, dataCriacao, dataValidade, working);

				}
	            fecharBanco(con);
				if (tokenNew == null) {	    
					return null;
				}else {
					return tokenNew;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}	
	}

	@Override
	public void desativarTokenFornecedor(int id) {
		Connection con = conectar();
		String query = String.format("update login_fornecedor set working = %s where id_fornecedor = %s", false, id); 
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.executeUpdate();
            fecharBanco(con);
		} catch (SQLException e) {
			e.printStackTrace();
			fecharBanco(con);
		}
	}



}
