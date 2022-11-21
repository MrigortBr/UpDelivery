package interfaces;


import entidade.Pessoa;

public interface IPessoaServico {
	public Pessoa verificarLogin(String login, String senha);
	public Pessoa verificarId(int id);
	public boolean criarRegistro(String nome, String sobrenome, String email, String cpf, String telefone, String dataNascimento,String senha);    
	public String vericiarRegistro(String email, String cpf, String telefone);
	public boolean validarSenha(String senha, String session);
	public boolean atualizar(String nome, String sobrenome, String email, String cpf, String telefone, String dataNascimento, String id);
	public boolean deletar(int id);
	public boolean aletarSenha(int id, String novaSenha);
}
