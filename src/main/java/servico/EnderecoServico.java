package servico;

import java.util.ArrayList;

import entidade.Endereco;
import interfaces.IEnderecoRepositorio;
import interfaces.IEnderecoServico;
import model.EnderecoRepositorio;

public class EnderecoServico implements IEnderecoServico{
	IEnderecoRepositorio repositorio = new EnderecoRepositorio();
	
	
	@Override
	public boolean registrar(String nome, int idUsuario, String rua, String bairro, String cidade, String numero, String cep, String complemento) {
		return repositorio.registrar(nome, idUsuario, rua, bairro, cidade, numero, cep, complemento);
	}

	@Override
	public boolean editar(String nome, int idUsuario, String rua, String bairro, String cidade, String numero, String cep, String complemento, int id) {
		return repositorio.editar(nome, idUsuario, rua, bairro, cidade, numero, cep, complemento, id);
	}

	@Override
	public ArrayList<Endereco> listar(int idUsuario) {
		return repositorio.listar(idUsuario);
	}

	@Override
	public boolean apagar(String nome, int idUsuario, int id) {
		return repositorio.apagar(nome, idUsuario, id);
	}
	
}
