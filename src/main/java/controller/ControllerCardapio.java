package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import interfaces.IProdutoServico;
import interfaces.ITokenServico;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servico.ProdutoServico;
import servico.TokenServico;


public class ControllerCardapio extends HttpServlet {
	private static final long serialVersionUID = 7L;
	
	IProdutoServico produtoServico = new ProdutoServico();
	ITokenServico tokenServico = new TokenServico();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeRequest = request.getParameter("method");
		
		switch (typeRequest) {
		case "atualizarProduto": {
			atualizarPedido(request, response);
			break;
		}
		case "registrarProduto":{
			registrarProduto(request, response);
			break;
		}
		case "deletarProduto":{
			deletarProduto(request, response);
			break;
		}
		}
	
	}


	private void deletarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idFornecedor = Integer.parseInt(tokenServico.pegarId(request));
		int idProduto = Integer.parseInt(request.getParameter("id"));
		
		boolean resposta = produtoServico.deletar(idFornecedor, idProduto);
		
		if (resposta == true) {
			response.getWriter().write("true");
		}else {
			response.getWriter().write("false");
		}
		
	}


	private void registrarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String tipo = request.getParameter("tipo");
		String preco = request.getParameter("preco");
		String desconto = request.getParameter("desconto");
		
		boolean resposta = produtoServico.registrar(Integer.parseInt(tokenServico.pegarId(request)), nome, descricao, preco, desconto, tipo);
	
		if (resposta == true) {
			response.getWriter().write("true");
		}else {
			response.getWriter().write("false");
		}
	}


	private void atualizarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String tipo = request.getParameter("tipo");
		String preco = request.getParameter("preco");
		String desconto = request.getParameter("desconto");

		
		boolean resposta = produtoServico.atualizar(Integer.parseInt(tokenServico.pegarId(request)), id, nome, descricao, preco, desconto, tipo);
		
		if (resposta == true) {
			response.getWriter().write("true");
		}else {
			response.getWriter().write("false");
		}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		// TODO Auto-generated method stub
		String typeRequest = request.getParameter("method");
		
		switch (typeRequest) {
		case "atualizarImagem": {
			atualizarImagem(request, response);
			break;
		}
		}
	}


	private void atualizarImagem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileUpload = request.getParameter("base64");
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		System.out.println(fileUpload);
	    if (fileUpload != null) {
            String fileUpload2 = fileUpload.replace("data:image/png;base64,", "");
            ServletContext ctx = getServletContext();
            String path = ctx.getRealPath( "/" );
            String pathType = "";
            pathType = "produtos/";
            File diretorio2 = new File(path+pathType+nome.replaceAll(" ", "")+id+".png");
            if (diretorio2.exists() ) {
            	diretorio2.delete();
            }
            try(FileOutputStream file = new FileOutputStream(new File(path+pathType+nome.replaceAll(" ", "")+id+".png"))) {
            	file.write(Base64.getDecoder().decode(fileUpload2));
            	response.getWriter().write("true");
            }catch (Exception e) {
                 response.getWriter().write("false");
            }          
         }else {
             response.getWriter().write("false");
         }
	}

}
