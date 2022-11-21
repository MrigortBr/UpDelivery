package interfaces;

import java.util.ArrayList;

import entidade.Cartao;

public interface ICartaoRepositorio {
	public boolean adicionar(int idPessoa, String apelido, String cartao, String cvv, String validade, String tipo, String bandeira);
	public boolean editar(int idPessoa, String apelido, String cartao, String cvv, String validade, String tipo, String bandeira, int id);
	public ArrayList<Cartao> listar(int idPessoa);
	public boolean apagar(int idPessoa, String cartao, int id);
	public Cartao buscarPorNomeEUsuario(int idUsuario, String cartao);

}
