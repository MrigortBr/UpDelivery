package servico;

import java.util.ArrayList;

import entidade.Fornecedor;
import entidade.Produto;
import interfaces.IFornecedorRepositorio;
import interfaces.IFornecedorServico;
import model.FornecedorRepositorio;

public class FornecedorServico implements IFornecedorServico{
	IFornecedorRepositorio repositorio = new FornecedorRepositorio();
	
	@Override
	public ArrayList<Fornecedor> listarPorId() {	
		return repositorio.listarPorId();
	}

	@Override
	public ArrayList<Fornecedor> listarPorAlfabeto() {
		return repositorio.listarPorAlfabeto();
	}

	@Override
	public ArrayList<Fornecedor> listarPorPrecoDeEntrega() {
		return repositorio.listarPorPrecoDeEntrega();
	}

	@Override
	public ArrayList<Fornecedor> listarPorTipo() {
		return repositorio.listarPorTipo();
	}

	@Override
	public ArrayList<Fornecedor> listarPorAvaliacao() {
		return repositorio.listarPorAvaliacao();
	}

	@Override
	public ArrayList<Fornecedor> buscarPorNome(String nomeFornecedor) {
		return repositorio.buscarPorNome(nomeFornecedor);
	}

	@Override
	public Fornecedor buscarPorId(int idFornecedor) {
		return repositorio.buscarPorId(idFornecedor);
	}

	@Override
	public Fornecedor validarLogin(String login, String senha) {
		return repositorio.validarLogin(login, senha);
	}

	@Override
	public ArrayList<String> registrar(String nome, String cnpj, String tipoEntrega, float precoEntrega, String email,String senha, String tipo, float avaliacao) {
		return repositorio.registrar(nome, cnpj, tipoEntrega, precoEntrega, email, senha, tipo, avaliacao);
	}
	
	public boolean validarSenha(String senha, String session) {
		return repositorio.validarSenha(senha, session);
	}


	@Override
	public boolean atualizar(int id, String nome, String cnpj, String tipoEntrega, float precoEntrega, String email, String tipo) {
		return repositorio.atualizar(id, nome, cnpj, tipoEntrega, precoEntrega, email, tipo);
	}

	@Override
	public boolean deletar(int id) {
		return repositorio.deletar(id);
	}

	@Override
	public boolean aletarSenha(int id, String novaSenha) {
		return repositorio.aletarSenha(id, novaSenha);
	}

	@Override
	public ArrayList<Produto> listarCardapio(int id) {
		return repositorio.listarCardapio(id);
	}
}
