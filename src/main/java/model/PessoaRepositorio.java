package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.Pessoa;
import interfaces.IPessoaRepositorio;

public class PessoaRepositorio extends DAO implements IPessoaRepositorio{
	
	@Override
	public Pessoa buscarUsuario(String query) {
		Connection con = conectar();
		ResultSet rs = null;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			rs = pst.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

		int id = 0;
		String nome = "";
		String sobrenome = "";
		String email = "";
		String cpf = "";
		String telefone = "";
		String dataNascimento = "";
		String senha2 = "";
		Pessoa pessoa = null;
		
		try {
			while (rs.next()) {
				 id = rs.getInt(1);
				 nome = rs.getString(2);
				 sobrenome = rs.getString(3);
				 email = rs.getString(4);
				 cpf = rs.getString(5);
				 telefone = rs.getString(6);
				 dataNascimento = rs.getString(7);
				 senha2 = rs.getString(8);
				 pessoa = new Pessoa(id, nome, sobrenome, email, cpf, telefone, dataNascimento, senha2);

			}

            fecharBanco(con);
			return pessoa;
		} catch (Exception e) {
            fecharBanco(con);
			return null;
		}
	}

	@Override
	public Pessoa buscarPorLogin(String login, String senha) {
		String query = String.format("select * from pessoa where email like'%s' and senha like'%s'", login, senha);
		return buscarUsuario(query);

	}

	@Override
	public Pessoa buscarPorId(int id) {
		String query = String.format("select * from pessoa where id like'%s'", id);
		return buscarUsuario(query);
	}

    @Override
    public ArrayList<String> verificarRegistro(String email, String cpf, String telefone) {
        Connection con = conectar();
        String query = String.format("select email,cpf,telefone from pessoa where email like '%s' or cpf like'%s' or telefone like '%s'", email, cpf, telefone);
        String queryValida = String.format("select email from pessoa where email like '%s'", email);

        ResultSet rs = null;
        PreparedStatement pst;
        ArrayList<String> resultado = new ArrayList<String>();
        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                if (rs.getString(1).equals(email)) {
                    resultado.add("email");
                }
                if (rs.getString(2).equals(cpf)) {
                    resultado.add("cpf");
                }
                if (rs.getString(3).equals(telefone)) {
                    resultado.add("telefone");
                }         
            }
            pst = con.prepareStatement(queryValida);
            rs = pst.executeQuery();
            while (rs.next()) {
            	if (rs.getString(1).equals(email)) {
            		resultado.add("email");
            	}
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        
        
        fecharBanco(con);
        return resultado;
    }

    @Override
    public boolean registrar(String nome, String sobrenome, String email, String cpf, String telefone,String dataNascimento, String senha) {
        Connection con = conectar();
        String query = String.format("insert into pessoa values (default, '%s', '%s', '%s', '%s', '%s', '%s', '%s')", nome, sobrenome, email, cpf, telefone, dataNascimento, senha);
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
    public boolean validarSenha(String senha, String session) {
        Connection con = conectar();
       
        String query = String.format("select login_usuario.id_usuario,pessoa.senha from login_usuario inner join pessoa on pessoa.id = login_usuario.id_usuario where working = 1 and senha like '%s' and token like '%s';", senha, session);
        System.out.println(query);
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
    public boolean atualizar(String id, String nome, String sobrenome, String email, String cpf, String telefone,String dataNascimento) {
        Connection con = conectar();
        String query = String.format("UPDATE pessoa SET nome = '%s', sobrenome = '%s', email = '%s', cpf = '%s', telefone = '%s', data_nascimento = '%s' WHERE (id = '%s')",nome, sobrenome, email, cpf, telefone, dataNascimento, id);
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

    public boolean deletar(int id) {
        Connection con = conectar();
        String query = String.format("delete from pessoa where id = %s", id);
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();
            fecharBanco(con);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }                   
        return false;
    }

    @Override
    public boolean aletarSenha(int id, String novaSenha) {
        Connection con = conectar();
        String query = String.format("UPDATE pessoa SET senha = '%s' where id = %s", novaSenha, id);
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.executeUpdate();
            fecharBanco(con);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }                   
        return false;
    }

}
