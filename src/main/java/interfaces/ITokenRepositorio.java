package interfaces;

import entidade.Token;

public interface ITokenRepositorio {
	public boolean adicionarToken(Token token);
	public Token autenticarToken(String token);
	public void desativarToken(int id);
	
	public boolean adicionarTokenFornecedor(Token token);
	public Token autenticarTokenFornecedor(String token);
	public void desativarTokenFornecedor(int id);
}
