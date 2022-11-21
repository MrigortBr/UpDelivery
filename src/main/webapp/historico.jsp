<%@page import="entidade.Fornecedor"%>
<%@page import="jakarta.servlet.ServletContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ page import="controller.Controller" %>

<%@ page import="entidade.Pessoa" %>
<%@ page import="entidade.Venda" %>
<%@ page import="entidade.Produto" %>

<%@ page import="interfaces.IProdutoServico" %>
<%@ page import="interfaces.IVendaServico" %>
<%@ page import="interfaces.ITokenServico" %>

<%@ page import="servico.ProdutoServico" %>
<%@ page import="servico.VendaServico" %>
<%@ page import="servico.TokenServico" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.File" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/historico.css">
        <script src="scripts/scripts.js"></script>
    

    <title>Historico</title>
</head>
<body>
		<% 
			ITokenServico token = new TokenServico();
			IVendaServico venda = new VendaServico(); 
		 	IProdutoServico prod = new ProdutoServico();
			ArrayList<Produto> produtos = prod.buscarDescontos();
			ArrayList<Venda> historico = new ArrayList<Venda>();
		%>
<header class="cabecalho-pagina">
        <div class="logo">
            <h1 class="logo"><a href="index.jsp">Logo</a></h1>
        </div>



        <div id="usuario">
            <i class="fa-regular fa-bell fa-xl" id="widget"></i>
            <i class="fa-regular fa-heart fa-xl" style="--fa-primary-color: red;" id="widget2"></i>
            <i class="fa-solid fa-clock-rotate-left fa-xl" style="--fa-primary-color: red;" id="widget3" onclick="window.location.href = 'historico.jsp'"></i>
            <i class="fa-solid fa-cart-shopping fa-xl" id="widget4" onclick="window.location.href = 'carrinho.jsp'"></i>

            <span id="texto-cabecalho">
                <%                   
            	String nome = "Fazer login";
            	String money = "";
            	String href = "";
            	String href2 = "";
            	String img = "users/";
        		if (token.autenticarLogin(request) == true){
					Object objeto = token.pegarUsuario(request);

					if (objeto instanceof Pessoa){
						Pessoa p = (Pessoa) objeto;
						nome = "Olá "+p.getNome();	
						img += p.getId() + p.getNome() +".png";
						img = img.replaceAll(" ", "_");


					}else if(objeto instanceof Fornecedor){
						Fornecedor p = (Fornecedor) objeto;
						nome = "Olá "+p.getNome();	
						img = "fornecedor/" + p.getId() + p.getNome() +".png";
						img = img.replaceAll(" ", "_");

					}
					
					href = "profile.jsp";
					href2 = "abrirConfiguracoes()";

	    		    ServletContext ctx = getServletContext();
	    		    String path = ctx.getRealPath( "/" );
	    		    File diretorio2 = new File(path+ "/"+img);    		    

	        		if (!diretorio2.exists()) {
	        			img = "users/imgUser.png";
	        		}
				}else{
        			img = "users/imgUser.png";
					href = "login.jsp";
					href2 = "window.location='login.jsp'";
				}
				%>
                <p><a href=<%=href %>><%=nome %></a></p>
                <p><a href=<%=href %>><%=money %></a></p>
            </span>
            <div id="configUsuario">
                <img src="<%=img %>" alt="" onclick=<%=href2%>>
                <ul id="configPerfil">
                    <li id="perfil"><a href="profile.jsp">Meu Perfil</a></li>
                    <%if(token.pegarUsuario(request) instanceof Pessoa){ %>
                    <li id="Cartoes"><a href="cartoes.jsp">Cartoes</a></li>
                    <li id="Endereco"><a href="endereco.jsp">Endereco</a></li>
                    <%}else{ %>
                    <li id="Cartoes"><a href="cardapio.jsp">Cardapio</a></li>
                    <%}; %>
                    <li id="finalizar"><a href="sair.jsp">Finalizar Sessão</a></li>
                </ul>
            </div>
        </div>
    </header>

    <section class="texto">
        <h1>Historico de pedidos</h1>
        <h2>Confira os seus pedidos feitos, seja um dia ou</h2>
    </section>

    <section class="filtros">
        <input type="month" name="" value="2022-08" min="2022-08"  id="input-mes" step="0"> 
        <select name="" id="select" val value="i">
            <option value="teste">Decrescente</option>
        </select>
        <label for="select">
        </label>
    </section>

    <section id="pedidos">
		<%
			for (Venda v : historico){
				Produto produto = prod.buscarProdutoPorId(v.getIdProduto());
		%>
		
		
		
        <div id="pedidoUm" class="fundo-pedidos" style="background-image: linear-gradient(to left, transparent 0%, rgba(78, 78, 78, 0.781) 75%), url(<%="produtos/"+produto.getNome().replace(" ", "")+produto.getId()+".png"%>) ;">
            <div class="pedido">
            <h1><%= v.getEstado() %> <br><a><%=produto.getNome() %></a></h1>
            </div>
            <div class="refazer-pedido">
                <button>Refazer</button>
            </div>
        </div>
        <% }; %>
    </section>


    <script src="https://kit.fontawesome.com/b1bb0593f9.js" crossorigin="anonymous"></script>
</body>
</html>