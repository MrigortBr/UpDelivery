package controller;

import java.io.IOException;

import interfaces.ICartaoServico;
import interfaces.ITokenServico;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servico.CartaoServico;
import servico.TokenServico;

public class ControllerCartao extends HttpServlet{
	private static final long serialVersionUID = 5L;
	ICartaoServico cartao = new CartaoServico();
	ITokenServico token = new TokenServico();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeRequest = request.getParameter("method");
		
		switch (typeRequest) {
			case "apagarCartao": {
				apagarCartao(request, response);
				break;
			}
		
		}
	}
	
	private void apagarCartao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean resposta = cartao.apagar(Integer.parseInt(token.pegarId(request)), request.getParameter("cartao"), Integer.parseInt(request.getParameter("id")));
		
		if (resposta == true) {
			response.getWriter().write("true");
		}else {
			response.getWriter().write("false");
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String typeRequest = request.getParameter("method");

		switch (typeRequest) {
			case "adicionarCartao": {
				adicionarCartao(request, response);
				break;
				}
			case "alterarCartao":{
				alterarCartao(request, response);
				break;
			}
		}

	}
	
	private void adicionarCartao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String nome = request.getParameter("nome");
		String numeroCartao = request.getParameter("cartao");
		String cvv = request.getParameter("cvv");
		String validade = request.getParameter("validade");
		String tipo = request.getParameter("tipo");
		String bandeira = request.getParameter("bandeira");
		
		boolean resposta = cartao.adicionar(Integer.parseInt(token.pegarId(request)), nome, numeroCartao, cvv, validade, tipo, bandeira);
		
		if (resposta == true) {
			response.getWriter().write("true");
		}else {
			response.getWriter().write("false");
		}
		
		
	}
	
	private void alterarCartao(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String nome = request.getParameter("nome");
		String numeroCartao = request.getParameter("cartao");
		String cvv = request.getParameter("cvv");
		String validade = request.getParameter("validade");
		String tipo = request.getParameter("tipo");
		String bandeira = request.getParameter("bandeira");
		int idCartao = Integer.parseInt(request.getParameter("id"));
		int idUser = Integer.parseInt(token.pegarId(request));
		
		boolean resposta = cartao.editar(idUser, nome, numeroCartao, cvv, validade, tipo, bandeira, idCartao);
		
		if (resposta == true) {
			response.getWriter().write("true");
		}else {
			response.getWriter().write("false");
		}
	}
	
}
