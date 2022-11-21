package servico;

import java.util.ArrayList;

import entidade.Cartao;
import interfaces.ICartaoRepositorio;
import interfaces.ICartaoServico;
import model.CartaoRepositorio;

public class CartaoServico implements ICartaoServico{
	ICartaoRepositorio repositorio = new CartaoRepositorio();

	@Override
	public boolean adicionar(int idPessoa, String apelido, String cartao, String cvv, String validade, String tipo,String bandeira) {
		return repositorio.adicionar(idPessoa, apelido, cartao, cvv, validade, tipo, bandeira);
	}

	@Override
	public boolean editar(int idPessoa, String apelido, String cartao, String cvv, String validade, String tipo,String bandeira, int id) {
		return repositorio.editar(idPessoa, apelido, cartao, cvv, validade, tipo, bandeira, id);
	}

	@Override
	public ArrayList<Cartao> listar(int idPessoa) {
		return repositorio.listar(idPessoa);
	}

	@Override
	public boolean apagar(int idPessoa, String cartao, int id) {
		return repositorio.apagar(idPessoa, cartao, id);
	}

	@Override
	public Cartao buscarPorNomeEUsuario(int idUsuario, String cartao) {
		System.out.println(cartao);
		return repositorio.buscarPorNomeEUsuario(idUsuario, cartao);
	}
	
	
	
}
