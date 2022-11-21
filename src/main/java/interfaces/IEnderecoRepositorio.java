package interfaces;

import java.util.ArrayList;

import entidade.Endereco;

public interface IEnderecoRepositorio {
	public boolean registrar(String nome, int idUsuario, String rua, String bairro, String cidade, String numero, String cep, String complemento);
	public boolean editar(String nome, int idUsuario, String rua, String bairro, String cidade, String numero, String cep, String complemento, int id);
	public ArrayList<Endereco> listar(int idUsuario);
	public boolean apagar(String nome, int idUsuario, int id);
}
