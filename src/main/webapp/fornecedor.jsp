<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Map"%>
<%@page import="entidade.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servico.ProdutoServico"%>
<%@page import="interfaces.IProdutoServico"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="servico.TokenServico"%>
<%@page import="interfaces.ITokenServico"%>
<%@page import="java.io.File"%>
<%@page import="jakarta.servlet.ServletContext"%>
<%@page import="entidade.Fornecedor"%>
<%@page import="entidade.Pessoa"%>
<%@page import="interfaces.IFornecedorServico"%>
<%@page import="servico.FornecedorServico"%>

<!DOCTYPE html>
<html>
<head>
	
	<%
			try{
			ITokenServico token = new TokenServico();
			IFornecedorServico fornecedor = new FornecedorServico();
			IProdutoServico produto = new ProdutoServico();
			int id = 1;
			try {
				id = Integer.parseInt(request.getParameter("fornecedor"));
			} catch (Exception e) {
				response.sendRedirect("index.jsp");
			}
			Fornecedor forn = fornecedor.buscarPorId(id);
			
	%>
	

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forncedor <%=forn.getNome() %></title>
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/mensagemErro.css">
    <link rel="stylesheet" href="styles/fornecedor.css">
        <script src="scripts/scripts.js"></script>
    
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

	<main>
        <div class="informacoesFornecedor">
            <img class="fotoFornecedor" src="fornecedor/<%=forn.getId()%><%=forn.getNome().replace(" ", "").toLowerCase()%>.png" alt="">
            <h1 class="nomeFornecedor"><%=forn.getNome()%></h1>
        </div>
        
        <%
        	ArrayList<Produto> produtos =  fornecedor.listarCardapio(forn.getId());
        	Map<String, ArrayList<Produto>> produtosListados = produto.dividirProdutosPorCategoria(produtos);
        	DecimalFormat formatter = new DecimalFormat("#.00");
        	if (produtosListados == null){
    			response.sendRedirect("index.jsp");
        	}	
        	else if (produtosListados.get("desconto") != null){              	
        %>
        <div class="categoria" id="categoriaDesconto">
            <p class="nomeCategoria"> <img src="files/desconto.svg" alt="">  Promoção</p>
            <div class="fundoProduto" id="fundoProdutoDesconto">     
            <%for (Produto prod: produtosListados.get("desconto")){ %>
                <div class="produto" id="produto<%=prod.getId()%>" onclick="location.href='produto.jsp?id=<%=prod.getId()%>'">
                    <span class="desconto"><%=(int)(prod.getDesconto()*100)%>%</span>
                    <img src="produtos/<%=prod.getNome().replace(" ", "")%><%=prod.getId()%>.png" alt="">
                    <span class="nomeProduto"><%=prod.getNome()%><br>R$ <%=formatter.format(prod.getPreco() * (1-prod.getDesconto())) %></span>
                </div>
 			<%};%>
 			</div>         
        </div>
        <%};%>
        
        <%for (String tipo: produtosListados.keySet()){
        	if(tipo != "desconto"){%>
        <div class="categoria" id="categoria<%=tipo%>">
	        <p class="nomeCategoria"> <img src="files/favorite.svg" alt=""><%=tipo%></p>
	        <div class="fundoProduto" id="fundoProduto<%=tipo%>">
        	<%for(Produto prod: produtosListados.get(tipo)){ %>
                <div class="produto" id="produto<%=prod.getId()%>" onclick="location.href='produto.jsp?id=<%=prod.getId()%>'">
                    <span class="desconto"><%=(int)(prod.getDesconto()*100)%>%</span>
                    <img src="produtos/<%=prod.getNome().replace(" ", "")%><%=prod.getId()%>.png" alt="">
                    <span class="nomeProduto"><%=prod.getNome()%><br>R$ <%=formatter.format(prod.getPreco() * (1-prod.getDesconto())) %></span>
        		</div>
        		
        <%}; %>
        	</div>
        </div>
        
        <%}}; %> 
    </main>
	<%}catch (Exception e) {
		}; %>
</body>
</html>