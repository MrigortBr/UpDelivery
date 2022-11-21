package interfaces;

import java.util.ArrayList;

import entidade.Fornecedor;
import entidade.Produto;

public interface IFornecedorServico {
	public ArrayList<Fornecedor> listarPorId();
	public ArrayList<Fornecedor> listarPorAlfabeto();
	public ArrayList<Fornecedor> listarPorPrecoDeEntrega();
	public ArrayList<Fornecedor> listarPorTipo();
	public ArrayList<Fornecedor> listarPorAvaliacao();
	public ArrayList<Fornecedor> buscarPorNome(String nomeFornecedor);
	public Fornecedor buscarPorId(int idFornecedor);
	public Fornecedor validarLogin(String login, String senha);
	public ArrayList<String> registrar(String nome, String cnpj, String tipoEntrega, float precoEntrega, String email,String senha, String tipo, float avaliacao);
	public boolean atualizar(int id, String nome, String cnpj, String tipoEntrega, float precoEntrega, String email, String tipo);
	public boolean validarSenha(String senha, String session);
	public boolean deletar(int id);
    public boolean aletarSenha(int id, String novaSenha);
    public ArrayList<Produto> listarCardapio(int id);
}
