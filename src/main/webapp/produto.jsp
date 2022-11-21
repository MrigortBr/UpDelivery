<%@page import="jakarta.servlet.ServletContext"%>
<%@page import="entidade.Fornecedor"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="controller.Controller" %>
<%@ page import="interfaces.ITokenServico" %>
<%@ page import="servico.TokenServico" %>
<%@ page import="entidade.Pessoa" %>
<%@ page import="interfaces.IProdutoServico" %>
<%@ page import="servico.ProdutoServico" %>
<%@ page import="entidade.Produto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.File" %>
<%@page import="java.text.DecimalFormat"%>

<!DOCTYPE html>
<%
	try{
	IProdutoServico prod = new ProdutoServico();

	int id = Integer.parseInt(request.getParameter("id"));
	Produto produto = prod.buscarProdutoPorId(id);
%>
<html>
	<head>
	    <link rel="stylesheet" href="styles/style.css">
	    <link rel="stylesheet" href="styles/mensagemErro.css">
	    <link rel="stylesheet" href="styles/produto.css">
	
	    <script src="scripts/produto.js"></script>
	    <script src="scripts/scripts.js"></script>
	    
		<meta charset="UTF-8">
		<title><%=produto.getNome() %></title>
	</head>
	<body>
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
        		ITokenServico token = new TokenServico();
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

					}else if(objeto instanceof Fornecedor){
						Fornecedor p = (Fornecedor) objeto;
						nome = "Olá "+p.getNome();	
						img = "fornecedor/" + p.getId() + p.getNome() +".png";
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
					response.sendRedirect("login.jsp");
				}
        		
				%>
                <p><a href=<%=href %>><%=nome %></a></p>
                <p><a href=<%=href %>><%=money %></a></p>
            </span>
            <div id="configUsuario">
                <img src="<%=img %>" alt="" onclick=<%=href2%>>
                <ul id="configPerfil">
                    <li id="perfil"><a href="profile.jsp">Meu Perfil</a></li>
                    <li id="finalizar"><a href="sair.jsp">Finalizar Sessão</a></li>
                </ul>
            </div>
        </div>
    </header>	
	    <div class="fundo-pagina">
	        <img src="produtos/<%=produto.getNome().replaceAll(" ", "")+produto.getId()%>.png" id="produto">
	        <div id="info">
	            <h1><%=produto.getNome() %></h1>
	            <p><%=produto.getDescricao()%></p>
	            <% 	String valor = "R$ ";
	            	String valorFinal = "R$ ";


	            	DecimalFormat formatter = new DecimalFormat("#.00");
	            if (produto.getDesconto() > 0.0){
	            	valor += formatter.format(produto.getPreco());
	            	valorFinal += formatter.format(produto.getPreco() * (1-produto.getDesconto()));

	            	
	            	
	            %>
	            
	           	<h2>De:<a style="text-decoration: line-through; color: red;"><%=valor%><a><br> Por:<%=valorFinal %></h2>
	            <% 
	            }else{
	            	valor += formatter.format(produto.getPreco());
	            	%>
	            
	            
	            <h2><%=valor%></h2>
	            <%}%>
	        </div>
	        <div id="carrinho" onclick="adicionarPedido()">
	            <svg id="adicionar" width="38" height="38" fill="none" stroke="#908e8e" stroke-linecap="round" stroke-linejoin="round" stroke-width="2.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" >
	            <path d="M9 20a1 1 0 1 0 0 2 1 1 0 1 0 0-2z"></path>
	            <path d="M20 20a1 1 0 1 0 0 2 1 1 0 1 0 0-2z"></path>
	            <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
	            </svg>
	        </div>
	        <div id="carrinho2">
	            <svg id="menos" width="38" height="38" fill="none" stroke="gray" stroke-linecap="round" stroke-linejoin="round" stroke-width="2.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" onclick="subtrair()">
	                <path d="M5 12h14"></path>
	                </svg>
	            <h2 id="contador">0</h2>
	            <svg id="mais" width="38" height="38" fill="none" stroke="gray" stroke-linecap="round" stroke-linejoin="round" stroke-width="2.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" onclick="adicionar()">
	                <path d="M12 5v14"></path>
	                <path d="M5 12h14"></path>
	            </svg>
	        </div>
	    </div>
	
	    <script src="https://kit.fontawesome.com/b1bb0593f9.js" crossorigin="anonymous"></script>
	    <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
		<%}catch(Exception e){
			response.sendRedirect("index.jsp");

			}%>
		
	</body>
</html>