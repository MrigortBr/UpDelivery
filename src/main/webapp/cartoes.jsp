<%@page import="entidade.Cartao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="servico.CartaoServico"%>
<%@page import="interfaces.ICartaoServico"%>

<%@page import="java.io.File"%>
<%@page import="entidade.Fornecedor"%>
<%@page import="jakarta.servlet.ServletContext"%>
<%@page import="entidade.Pessoa"%>
<%@page import="servico.TokenServico"%>
<%@page import="interfaces.ITokenServico"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/mensagemErro.css">
    
    <link rel="stylesheet" href="styles/cartoes.css">
    <script src="scripts/scripts.js"></script>
    <script src="scripts/cartao.js"></script>
    
	<title>Cartoes</title>
</head>
<body onload="loadCartoes()">

		<% 
			ITokenServico token = new TokenServico();
			ICartaoServico cartao = new CartaoServico();
			ArrayList<Cartao> cartoes =  cartao.listar(Integer.parseInt(token.pegarId(request)) );
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
						response.sendRedirect("index.jsp");

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
    <div id="page">
        <h1 class="informativos">Cartões</h1>		
    
    	<%  
        	if (cartoes.isEmpty()){
        %>
        
        <p>Você não tem cartoes registrados.</p>
        
        <%		
        	}else{
        		
        	}
    		for (Cartao card : cartoes){        		 		
    	%>

        <div class="cardDiv" id="cardDiv<%=card.getId()%>">
            <div class="headerCard">
                <img class="svgBank" id="bank<%=card.getId()%>" src="bancos/<%=card.getBandeira()%>.svg" alt="">
                <input type="text" class="surnameCard" id="surnameCardInput<%=card.getId()%>" disabled value='<%=card.getApelido()%>'>
                <p class="tipo" id="tipo<%=card.getId()%>"><%=card.getTipo()%> ></p>

            </div>
            <span class="informationCard">
                <p class="numberCard">Numero:
                    <input type="password" id="cardInput<%=card.getId()%>" class="cardInput" value=<%=card.getCartao() %> onkeyup="alterarCartao('<%=card.getId()%>')" maxlength="16" disabled> 
                    <img id="olho<%=card.getId()%>" src="files/olhofechado.svg" alt="" onclick="mostrarCartao('<%=card.getId()%>')">
                </p>
                <p class="validityCard" id="validityCard<%=card.getId()%>">Validade: <input class="validityCardInput" id="validityCardInput<%=card.getId()%>" type="text" value=<%=card.getValidade() %>  maxlength="5" disabled></p>
                <p class="codeCard" id="codeCard<%=card.getId()%>">CVV: <input class="codeCardInput" id="codeCardInput<%=card.getId()%>" type="text" value=<%=card.getCvv()%>  maxlength="3" disabled></p>
            </span>
            <div class="optionsCard">
                <img class="svgDelete" id="svgDelete<%=card.getId()%>" src="files/delete.svg" alt="" onclick="deletar('<%=card.getId()%>')">
                <img class="svgEdit" id="svgEdit<%=card.getId()%>" src="files/edit.svg" alt="" onclick="editar('<%=card.getId()%>')">
            </div>            
        </div>
        <%}; %>
        <h1 class="informativos">Adicionar Cartao</h1>		
        <div class="cardDiv"  id="cardDiv0">
            <div class="headerCard">
                <img class="svgBank" id="bank0" src="bancos/mastercard.svg" alt="">
                <input type="text" class="surnameCard" id="surnameCardInput0" value="Novo Cartão">
                <p class="tipo" id="tipo0" onclick="mudarTipo('0')")>Debito ></p>
                
            </div>
            <span class="informationCard">
                <p class="numberCard">Numero:
                    <input type="text" id="cardInput0" class="cardInput" value="0000 0000 0000 0000" onkeyup="lerCartaoNumero('0')" maxlength="19"> 
                    <img id="olho0" src="files/olhoaberto.svg" alt="" onclick="mostrarCartao('0')">
                </p>
                <p class="validityCard" id="validityCard0">Validade: <input class="validityCardInput" id="validityCardInput0" type="text" value="--/--"  maxlength="5"></p>
                <p class="codeCard" id="codeCard0">CVV: <input class="codeCardInput" id="codeCardInput0" type="text" value="---"  maxlength="3"></p>
            </span>
            <div class="optionsCard">
                <img class="svgDelete" id="svgCheck" src="files/check-circle.svg" alt="" onclick="adicionar()">
                <img class="svgEdit" id="svgX" src="files/x-circle.svg" alt="" onclick="cancelarNovo()">
            </div>            
        </div>
        
        
	    </div>
	    <div id="principal">
	        <svg  id="img" viewBox="0 0 60 60" xmlns="http://www.w3.org/2000/svg" >
	            <path id="imgPath" d="m10.497 32.281c4.2095 3.8308 6.93 7.2788 11.433 15.27 9.789-24.709 23.193-31.053 21.493-32.147-3.4388-2.3075-20.169 17.034-22.192 19.586-2.4332-1.2882-10.733-5.791-10.733-2.7086z" fill="#333" fill-rule="evenodd" stroke="#333" stroke-linecap="round" stroke-linejoin="round" stroke-width="1.875"/>
	        </svg>
	        <svg id="img2" viewBox="0 0 18 18" xmlns="http://www.w3.org/2000/svg" >
	            <path id="img2Path" d="m176.068,407.132 .375.551c-.033.134-.117.283-.251.45-.067.05-.101.101-.101.151 0,.016.017.058.05.125-2.153,2.17-3.979,4.206-5.482,6.108 2.02,2.671 3.254,4.2 3.705,4.582-.084.468-.267.827-.551,1.077l-.376.3c-.266.219-.399.335-.399.353v.023l.075.275c-.068.033-.127.092-.176.175-.184.218-.385.369-.601.452-.067,0-.15-.05-.252-.151-.066-.048-.149-.108-.249-.175l-.149-.101c-.018-.016-.034-.025-.051-.025l-.076.051c-.35.435-.667.71-.951.827-.05,0-.117-.025-.2-.076-.534-.549-1.101-1.268-1.702-2.152-.5-.751-.875-1.287-1.127-1.604l-.249.35-1.728,2.255c-.217.284-.834,1.136-1.853,2.553h-.326l-.477-.524 .052-.201 .049-.149c0-.1-.05-.151-.15-.151s-.25.052-.45.151c-.018,0-.067.024-.15.075-.184-.101-.451-.435-.802-1.001-.1-.149-.149-.251-.149-.301 0-.033.042-.109.125-.226 .117-.185.175-.334.175-.45 0-.033-.083-.228-.25-.576l-.124-.375 1.226-1.578 2.729-3.153c.067-.102.134-.185.2-.251-1.084-1.886-1.968-3.755-2.653-5.609-.15-.451-.241-.768-.275-.951 .234-.367.542-.736.927-1.103l.375.301c.066.117.133.175.2.175 .167,0 .275-.124.325-.375 .051-.468.259-.71.627-.726 .117,0 .275.108.475.324 1.053,1.72 2.063,3.247 3.029,4.583 2.172-2.387 3.964-4.231 5.384-5.534 .284-.25.441-.375.476-.375 .083,0 .334.208.752.625l.149.201c0,.033-.025.091-.075.176-.033.033-.049.066-.049.099 0,.05.149.184.448.402l.377-.1 .149.223z"
	              fill="#333" transform="translate(-158.86 -405.51)"/>
	        </svg>
	        <div id="mensagem">
	            <h1 id="titulo">Erro tal</h1>
	            <h2 id="erro">Motivo do erro</h1>
	        </div>
	    </div>
	<script src="https://kit.fontawesome.com/b1bb0593f9.js" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</body>
</html>