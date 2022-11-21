<%@page import="interfaces.IFornecedorServico"%>
<%@page import="servico.FornecedorServico"%>
<%@page import="java.io.File"%>
<%@page import="jakarta.servlet.ServletContext"%>
<%@page import="entidade.Fornecedor"%>
<%@page import="entidade.Pessoa"%>
<%@page import="servico.TokenServico"%>
<%@page import="interfaces.ITokenServico"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entidade.Produto"%>
<%@page import="interfaces.IProdutoServico"%>
<%@page import="servico.ProdutoServico"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<%
	String titulo = "";
	ArrayList<Produto> produtos = null;
	IProdutoServico prod = new ProdutoServico();
	if(request.getParameter("busca") != null){
		titulo = request.getParameter("busca");
    	produtos = prod.buscarProdutoPorNome(titulo);
	}else{
		titulo = request.getParameter("tipo");
    	produtos = prod.buscarPorTipo(titulo);
	};

%>
	<title>Up busca: <%=titulo %></title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/busca.css">
    <script src="scripts/index.js"></script>
        <script src="scripts/scripts.js"></script>
    

</head>
<body>
	    <header class="cabecalho-pagina">
        <div class="logo">
            <h1 class="logo"><a href="index.jsp">Logo</a></h1>
        </div>

		<% 
			IFornecedorServico fornecedor = new FornecedorServico();
		%>

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
    
    <section class="busca">
        <section class="texto-busca">
            <h1>
                Peça o que quiser
            </h1>
            <h4>e pode relaxa, nós fazemos o resto por você </h2>
        </section>
        <section class="busca-completa">
            <div class="fundo-busca">
                <input type="text" placeholder="Busque sua comida" id="input">
                <i class="fa-solid fa-magnifying-glass" onclick="buscarPedido()"></i>
            </div>
        </section>
    </section>
    

    <section id="fundoPagina">
        <div id="produtosFundo">
        <%if (produtos.isEmpty()){ %>
            <div id="imgFundo">
                <img src="assets/error.gif" alt="" id="img">
                <p id="" style="text-align: center;">Produto Indisponivel</p>
            </div>
        </div>
		<%}else{
        
                
        	for(Produto produto : produtos){
        		int desconto = 0;
        		String descontoFinal = " - ";
        		if (produto.getDesconto() > 0.01){
    	            desconto = (int) (produto.getDesconto()*100);	
        			descontoFinal += Integer.toString(desconto) + "% OFF"; 
        		}else{
        			descontoFinal = "";
        		}
        	%>
	        <div id="imgFundo">
	            <img src="produtos/<%=produto.getNome().replace(" ", "")+produto.getId()%>.png" alt="" id="img" onclick="window.location.href = 'produto.jsp?id=<%=produto.getId() %>'">
	            <p id="textoImg"><%=produto.getNome() %><b><%=descontoFinal%></b></p>
	        </div>
	        <%}} %>
    	</div>

    </section>
    
    
     <footer class="rodape-1">
            <div class="lado-esquerdo-rodape-1">
                <h2>Logo</h2>
                <p>©2022, All right reserved.</p>
            </div>
            <div class="lado-direito-rodape-1">
                <i class="fa-brands fa-facebook-f"></i>
                <i class="fa-brands fa-instagram"></i>
                <i class="fa-brands fa-twitter"></i>
                <i class="fa-brands fa-linkedin"></i>
            </div>
        </div>
    </footer>
    <script src="https://kit.fontawesome.com/b1bb0593f9.js" crossorigin="anonymous"></script>
</body>
</html>