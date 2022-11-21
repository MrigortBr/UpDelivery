package controller;

import java.io.IOException;

import interfaces.IEnderecoServico;
import interfaces.ITokenServico;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servico.EnderecoServico;
import servico.TokenServico;

public class ControllerEndereco extends HttpServlet{
	private static final long serialVersionUID = 6L;
	IEnderecoServico endereco = new EnderecoServico();
	ITokenServico token = new TokenServico();

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeRequest = request.getParameter("method");
		
		switch (typeRequest) {
			case "apagarEndereco": {
				apagarEndereco(request, response);
				break;
			}
			case "adicionarEndereco":{
				adicionarEndereco(request, response);
				break;
			}
		
		}
	}
	
	private void apagarEndereco(HttpServletRequest request, HttpServletResponse response) throws IOException {

		boolean resposta = endereco.apagar(request.getParameter("nome"), Integer.parseInt(token.pegarId(request)), Integer.parseInt(request.getParameter("id")));
		
		if (resposta == true) {
			response.getWriter().write("true");
		}else {
			response.getWriter().write("false");
		}
	}
	
	private void adicionarEndereco(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nome = request.getParameter("nome");
		String rua = request.getParameter("rua");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String numero = request.getParameter("numero");
		String cep = request.getParameter("cep");
		String complemento = request.getParameter("complemento");
		int id = Integer.parseInt(token.pegarId(request));
		
		boolean resposta = endereco.registrar(nome, id, rua, bairro, cidade, numero, cep, complemento);
		
		if (resposta == true) {
			response.getWriter().write("true");
		}else {
			response.getWriter().write("false");
		}

	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeRequest = request.getParameter("method");
	}
}
