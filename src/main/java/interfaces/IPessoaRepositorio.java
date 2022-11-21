package interfaces;

import java.util.ArrayList;

import entidade.Pessoa;

public interface IPessoaRepositorio {
	public Pessoa buscarUsuario(String query);
	public Pessoa buscarPorLogin(String login, String senha);
	public Pessoa buscarPorId(int id);
	public ArrayList<String> verificarRegistro(String email, String cpf, String telefone);
	public boolean registrar(String nome, String sobrenome, String email, String cpf, String telefone, String dataNascimento,String senha);
	public boolean validarSenha(String senha, String session);
	public boolean atualizar(String id, String nome, String sobrenome, String email, String cpf, String telefone, String dataNascimento);
	public boolean deletar(int id);
    public boolean aletarSenha(int id, String novaSenha);
}
