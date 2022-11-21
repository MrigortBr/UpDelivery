package controller;

import java.io.IOException;

import interfaces.ITokenServico;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servico.TokenServico;



public class ControllerProduto extends HttpServlet {
	private static final long serialVersionUID = 9L;
	
	ITokenServico token = new TokenServico();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeRequest = request.getParameter("method");
		
		switch (typeRequest) {
			case "retirarCarrinho": {
				retirarCarrinho(request, response);
				break;
			}
			case "adicionarCarrinho": {
				adicionarCarrinho(request, response);
				break;
			}
		
		}
	}


	private void adicionarCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idProduto = Integer.parseInt(request.getParameter("id"));
		int quantidade = Integer.parseInt(request.getParameter("quantidade"));

		
		boolean resposta = token.adicionarProdutoCarrinho(response, idProduto, quantidade);
		
		
		if (resposta == true) {
			response.getWriter().write("true");
		}else {
			response.getWriter().write("false");
		}
		
	}


	private void retirarCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idProduto = Integer.parseInt(request.getParameter("id"));
		int quantidade = Integer.parseInt(request.getParameter("quantidade"));
	
		boolean resposta = token.retirarProdutoCarrinho(request, response, idProduto, quantidade);

		if (resposta == true) {
			response.getWriter().write("true");
		}else {
			response.getWriter().write("false");
		}		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
