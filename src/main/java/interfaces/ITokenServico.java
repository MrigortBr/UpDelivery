package interfaces;

import java.util.Map;

import entidade.Fornecedor;
import entidade.Pessoa;
import entidade.Produto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ITokenServico {
	public boolean criarToken(Object objeto, HttpServletResponse response);
	public boolean autenticarLogin(HttpServletRequest request);
	public void zerarCookie(HttpServletRequest request, HttpServletResponse response);
	public Object pegarUsuario(HttpServletRequest request);
	public String pegarToken(HttpServletRequest request);
	public String pegarId(HttpServletRequest request);
	public String pegarTipoUsuario(HttpServletRequest request);
	public Map<Produto, Integer> pegarCarrinho(HttpServletRequest request);
	public boolean adicionarProdutoCarrinho(HttpServletResponse response, int id, int quantidade);
	public boolean retirarProdutoCarrinho(HttpServletRequest request, HttpServletResponse response, int id, int quantidade);

}
