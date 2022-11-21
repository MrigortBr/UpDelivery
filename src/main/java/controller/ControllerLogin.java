package controller;

import java.io.IOException;

import interfaces.IFornecedorServico;
import interfaces.IPessoaServico;
import interfaces.ITokenServico;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servico.FornecedorServico;
import servico.PessoaServico;
import servico.TokenServico;

public class ControllerLogin extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private IPessoaServico pessoa = new PessoaServico();
	private IFornecedorServico fornecedor = new FornecedorServico();

	private ITokenServico token = new TokenServico();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeRequest = request.getParameter("method");
		switch (typeRequest) {
		case "login": {
			responderLogin(request.getParameter("login"), request.getParameter("senha"), response);
			break;
		}
	}}
	
	 private void responderLogin(String login, String senha, HttpServletResponse response) throws IOException {
			//Resposta da verificação de login
	    	Object entidade = null;
	    	

			    entidade = pessoa.verificarLogin(login, senha);
			    if (entidade == null) {
					entidade = fornecedor.validarLogin(login, senha);
			    }
			    
			if (entidade != null) {
				Boolean resposta = token.criarToken(entidade, response);
				if (resposta == false) {
					response.getWriter().write("false");
				}else {
					response.getWriter().write("true");
				}
			}else {
				response.getWriter().write("false");
			}
		}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
