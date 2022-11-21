package controller;

import java.io.IOException;
import java.util.Map;

import com.google.protobuf.MapEntry;

import entidade.Cartao;
import entidade.Produto;
import interfaces.ICartaoServico;
import interfaces.IProdutoServico;
import interfaces.ITokenServico;
import interfaces.IVendaServico;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servico.CartaoServico;
import servico.ProdutoServico;
import servico.TokenServico;
import servico.VendaServico;

public class ControllerVenda extends HttpServlet {
	private static final long serialVersionUID = 9L;
	IVendaServico vendaServico = new VendaServico();
	ITokenServico tokenServico = new TokenServico();
	IProdutoServico produtoServico = new ProdutoServico();
	ICartaoServico cartaoServico = new CartaoServico();




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeRequest = request.getParameter("method");
		switch (typeRequest) {
		case "venderProduto": {
			venderProduto(request, response);
			break;
		}case "apagarProduto":{
			apagarProduto(request, response);
			break;
			
		}
		}
	}
	
	private void apagarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idFornecedor = Integer.parseInt(request.getParameter("fornecedor"));
		Map<Produto, Integer> map = produtoServico.apagarFornecedor(idFornecedor, tokenServico.pegarCarrinho(request));
		for (Map.Entry<Produto, Integer> entry : map.entrySet()) {
			boolean respostaVerifica = tokenServico.retirarProdutoCarrinho(request, response, entry.getKey().getId(), entry.getValue());
			response.getWriter().write("true");
		}
	}


	private void venderProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int idUsuario = Integer.parseInt(tokenServico.pegarId(request));
		int idFornecedor = Integer.parseInt(request.getParameter("fornecedor"));
		Cartao cartao = cartaoServico.buscarPorNomeEUsuario(idUsuario, request.getParameter("nomeCartao"));

		String tipoPagamento = request.getParameter("nomeCartao");
		String estado = "Pedido Entregue";
		System.out.println(request.getParameter("nomeCartao"));
		System.out.println(cartao);
		Map<Produto, Integer> map = produtoServico.apagarFornecedor(idFornecedor, tokenServico.pegarCarrinho(request));
		
		for (Map.Entry<Produto, Integer> entry : map.entrySet()) {
			float preco = entry.getKey().getPreco();
			float precoFinal = entry.getKey().getPreco() * (1-entry.getKey().getDesconto());
			int idProduto = entry.getKey().getId();
			boolean  respostaVerifica = false;

			if (cartao != null) {
				respostaVerifica = vendaServico.registrar(idUsuario, idProduto, idFornecedor, preco, entry.getKey().getDesconto(), precoFinal, entry.getValue(), cartao.getTipo(), cartao.getCartao(), estado);
			}else{
				respostaVerifica = vendaServico.registrar(idUsuario, idProduto, idFornecedor, preco, entry.getKey().getDesconto(), precoFinal, entry.getValue(), "Desconhecido", null, estado);
			}
			
			if (respostaVerifica == true) {
				response.getWriter().write("true");
				tokenServico.retirarProdutoCarrinho(request, response, entry.getKey().getId(), entry.getValue());
			}else {
				response.getWriter().write("false");
			}
		}	
	}

}
