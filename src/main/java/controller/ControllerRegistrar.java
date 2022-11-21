package controller;

import java.io.IOException;
import java.util.ArrayList;

import interfaces.IFornecedorServico;
import interfaces.IPessoaServico;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servico.FornecedorServico;
import servico.PessoaServico;

public class ControllerRegistrar extends HttpServlet {
	private static final long serialVersionUID = 3L;
	private IPessoaServico pessoa = new PessoaServico();
	private IFornecedorServico fornecedor = new FornecedorServico();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeRequest = request.getParameter("method");
		switch (typeRequest) {
			case "registrarPessoa":
			    responderRegistroPessoa(request.getParameter("nome"), request.getParameter("sobrenome"), request.getParameter("email"), request.getParameter("cpf"), request.getParameter("telefone"), request.getParameter("dataNasc"), request.getParameter("senha"), response);
			    break;
			case "registrarFornecedor":
				responderRegistroFornecedor(request.getParameter("nome"), request.getParameter("cnpj"), request.getParameter("tipoEntrega"), request.getParameter("precoEntrega"), request.getParameter("email"), request.getParameter("senha"), request.getParameter("tipo"), 0, response);
		}
	}
	
	private void responderRegistroPessoa(String nome, String sobrenome, String email, String cpf, String telefone, String dataNascimento, String senha, HttpServletResponse response) throws IOException {
	    String respostaVerifica = pessoa.vericiarRegistro(email, cpf, telefone);
	    if (respostaVerifica.isEmpty() == true) {
	        pessoa.criarRegistro(nome, sobrenome, email, cpf, telefone, dataNascimento, senha);
	        response.getWriter().write("true");
	    }else{
	       response.getWriter().write(respostaVerifica);
	    }
	}
	
	private void responderRegistroFornecedor(String nome, String cnpj, String tipoEntrega, String precoEntrega, String email,String senha, String tipo, float avaliacao, HttpServletResponse response) throws IOException {
		ArrayList<String> respostaVerifica = fornecedor.registrar(nome, cnpj, tipoEntrega,Float.parseFloat(precoEntrega), email, senha, tipo, avaliacao);

		if (respostaVerifica.isEmpty()) {
			response.getWriter().write("true");
		}else {
			response.getWriter().write(respostaVerifica.get(0));
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	

}
