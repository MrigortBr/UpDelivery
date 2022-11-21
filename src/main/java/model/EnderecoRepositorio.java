package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.Endereco;
import interfaces.IEnderecoRepositorio;

public class EnderecoRepositorio extends DAO implements IEnderecoRepositorio{

	@Override
	public boolean registrar(String nome, int idUsuario, String rua, String bairro, String cidade, String numero, String cep, String complemento) {
		Connection con = conectar();
		String query = String.format("insert into endereco_pessoa values(default, '%s','%s', '%s', '%s', '%s', '%s', '%s', '%s')", nome, idUsuario, rua, bairro, cidade,  numero, cep, complemento);
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
	public boolean editar(String nome, int idUsuario, String rua, String bairro, String cidade, String numero, String cep, String complemento, int id) {
		Connection con = conectar();
		String query = String.format("UPDATE endereco_pessoa SET nome = '%s', id_usuario = '%s', rua = '%s', numero = '%s', cep = '%s', complemento = '%s' WHERE id = '%s'", nome, idUsuario, rua, numero, cep, complemento, id);
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
	public ArrayList<Endereco> listar(int idUsuario) {
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		Connection con = conectar();
		String query = String.format("select * from endereco_pessoa where id_usuario = %s",idUsuario);
		try {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			int id = 0;
			String nome = "";
			int idUser = 0;
			String rua = "";
			String bairro = "";
			String cidade = "";
			String numero = "";
			String cep = "";
			String complemento = "";
			while(rs.next()) {
				id = rs.getInt(1);
				nome = rs.getString(2);
				idUser = rs.getInt(3);
				rua = rs.getString(4);
				bairro = rs.getString(5);
				cidade = rs.getString(6);
				numero = rs.getString(7);
				cep = rs.getString(8);
				complemento = rs.getString(9);
				enderecos.add(new Endereco(id, nome, idUser, rua, bairro, cidade, numero, cep, complemento));
			}
			
			fecharBanco(con);
			return enderecos;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public boolean apagar(String nome, int idUsuario, int id) {
		Connection con = conectar();
        String query = String.format("delete from endereco_pessoa where id_usuario = %s and id = %s and nome like '%s'", idUsuario, id, nome);
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

}
