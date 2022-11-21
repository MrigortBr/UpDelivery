package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.Cartao;
import interfaces.ICartaoRepositorio;

public class CartaoRepositorio extends DAO implements ICartaoRepositorio{

	@Override
	public boolean adicionar(int idPessoa, String apelido, String cartao, String cvv, String validade, String tipo, String bandeira) {
		Connection con = conectar();
		String query = String.format("insert into pagamento_usuario values(%s, '%s','%s', '%s', '%s', '%s', '%s', default)", idPessoa, apelido, cartao, cvv, validade, tipo, bandeira);
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.executeUpdate();
			fecharBanco(con);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	public boolean editar(int idPessoa, String apelido, String cartao, String cvv, String validade, String tipo, String bandeira, int id) {
		Connection con = conectar();
		String query = String.format("update pagamento_usuario set apelido = '%s', cartao = '%s', cvv = '%s', validade = '%s', tipo = '%s', bandeira = '%s' where id_usuario = %s and id = %s ", apelido, cartao, cvv, validade, tipo, bandeira, idPessoa, id);
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.executeUpdate();
			fecharBanco(con);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	public ArrayList<Cartao> listar(int idPessoa) {
		ArrayList<Cartao> cartoes = new ArrayList<Cartao>();
		Connection con = conectar();
		String query = String.format("select * from pagamento_usuario where id_usuario = %s",idPessoa);
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			int idUsuario = 0;
			String apelido = "";
			String cartao = "";
			String cvv = "";
			String validade = "";
			String tipo = "";
			String bandeira = "";
			
			int id = 0;
			while(rs.next()) {
				idUsuario = rs.getInt(1);
				apelido = rs.getString(2);
				cartao = rs.getString(3);
				cvv = rs.getString(4);
				validade = rs.getString(5);
				tipo = rs.getString(6);
				bandeira = rs.getString(7);
				id = rs.getInt(8);
				
				cartoes.add(new Cartao(idUsuario, apelido, cartao, cvv, validade,tipo, bandeira, id));	
			}
			fecharBanco(con);
			return cartoes;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public boolean apagar(int idPessoa, String cartao, int id) {
		Connection con = conectar();
        String query = String.format("delete from pagamento_usuario where id_usuario = %s and id = %s and cartao like %s", idPessoa, id, cartao);
        try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.executeUpdate();
			fecharBanco(con);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	public Cartao buscarPorNomeEUsuario(int idUs, String card) {
		Connection con = conectar();
        String query = String.format("select * from pagamento_usuario where id_usuario = '%s' and apelido LIKE '%s'", idUs, card);
        Cartao reposta = null;
        try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			int idUsuario = 0;
			String apelido = "";
			String cartao = "";
			String cvv = "";
			String validade = "";
			String tipo = "";
			String bandeira = "";
			
			int id = 0;
			while(rs.next()) {
				idUsuario = rs.getInt(1);
				apelido = rs.getString(2);
				cartao = rs.getString(3);
				cvv = rs.getString(4);
				validade = rs.getString(5);
				tipo = rs.getString(6);
				bandeira = rs.getString(7);
				id = rs.getInt(8);
				
				reposta = new Cartao(idUsuario, apelido, cartao, cvv, validade,tipo, bandeira, id);	
			}
			fecharBanco(con);
			return reposta;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

}
