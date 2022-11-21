<%@page import="jakarta.servlet.ServletContext"%>
<%@page import="entidade.Fornecedor"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="controller.Controller" %>
<%@ page import="interfaces.ITokenServico" %>
<%@ page import="servico.TokenServico" %>
<%@ page import="entidade.Pessoa" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.File" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Usuario</title>
    <link rel="stylesheet" href="styles/profile.css">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/senha.css">
    <link rel="stylesheet" href="styles/mensagemErro.css">

    <script src="scripts/profile.js"></script>
    <script src="scripts/scripts.js"></script>

</head>
<body>
    <header class="cabecalho-pagina">
        <div class="logo">
            <h1 class="logo"><a href="index.jsp">Logo</a></h1>
        </div>

		<% 
		   ITokenServico token = new TokenServico();
		%>

        <div id="usuario">
            <i class="fa-regular fa-bell fa-xl" id="widget"></i>
            <i class="fa-regular fa-heart fa-xl" style="--fa-primary-color: red;" id="widget2"></i>
            <i class="fa-solid fa-clock-rotate-left fa-xl" style="--fa-primary-color: red;" id="widget3" onclick="window.location.href = 'historico.jsp'"></i>
            <i class="fa-solid fa-cart-shopping fa-xl" id="widget4" onclick="window.location.href = 'carrinho.jsp'"></i>

            <span id="texto-cabecalho">
                <%
                String funcion = "";
            	String nome = "Fazer login";
            	String img = "users/";
            	String nomeUsuario = "";
            	String sobreNome = "";
            	String CPF = "";
            	String telefone = "";
            	String dataNasc = "";
            	String email = "";
            	Object objeto = null;
        		if (token.autenticarLogin(request) == true){
        			objeto = token.pegarUsuario(request);
        			
        			if (objeto instanceof Pessoa){
						Pessoa p = (Pessoa) objeto;
        				nome = "Olá "+p.getNome();	
    					img += p.getId() + p.getNome() +".png";
						img.replaceAll(" ", "_");
    	            	nomeUsuario = p.getNome();
    	            	sobreNome = p.getSobrenome();
    	            	CPF = p.getCpf();
    	            	telefone = p.getTelefone();
    	            	dataNasc = p.getDataNascimento();
    	            	email = p.getEmail();

					}else if(objeto instanceof Fornecedor){
						Fornecedor p = (Fornecedor) objeto;
						nome = "Olá "+p.getNome();	
						img = "fornecedor/" + p.getId() + p.getNome() +".png";
						img = img.replaceAll(" ", "_");
    	            	nomeUsuario = p.getNome();
    	            	sobreNome = p.getEmail();
    	            	CPF = p.getCnpj();
    	            	telefone = p.getTipoEntrega();
    	            	dataNasc = Float.toString(p.getPrecoEntrega());
    	            	email = p.getTipo();
					}       								
	    		    ServletContext ctx = getServletContext();
	    		    String path = ctx.getRealPath( "/" );
	    		    File diretorio2 = new File(path+ "/"+img);

	     		    
	        		if (!diretorio2.exists()) {
	        			img = "users/imgUser.png";
	        		}
				}else{
					if (token.autenticarLogin(request) == false){
						response.sendRedirect("login.jsp");
					}
				}
        		

        		
				%>
                <p><%=nome%></p>
            </span>
            <img src="<%=img%>" alt="">
        </div>
    </header>

    <!-- Conteudo da pagina -->

    <div class="fundoInformacoes">
        <div id="grid">
            <div id="imgPreview">
                <img src=<%=img %> alt="" onclick="pegarArquivo()" id="imgUsuario">
            </div>
            <%if (objeto instanceof Pessoa){ %>
            <div id="nomeSection">
                <input type="text" name="nome" id="nome" class='campos' required placeholder="Nome" value=<%=nomeUsuario %>>
            </div>
            <div id="sobrenomeSection">               
                <input type="text" name="sobrenome" id="sobrenome" class='campos' required placeholder="SobreNome" value=<%=sobreNome %>>
            </div>
            <div id="cpfSection">
                <input type="text" name="cpf" id="cpf" class='campos' required maxlength='11' placeholder="CPF" value=<%=CPF %>>
            </div>
            <div id="telefoneSection">
                <input type="text" name="telefone" id="telefone" class='campos' required maxlength='11' placeholder="Telefone" value=<%=telefone %>>
            </div>

            <div id="datasNascSection">
                <input type="date" name="datasNasc" id="datasNasc" class='campos' required placeholder="Data" value=<%=dataNasc %>>
            </div>

            <div id="emailSection">
                <input type="text" name="email" id="email" class='campos' required placeholder="Email" value=<%=email %>>
            </div>
            
            <%}else{
				Fornecedor p = (Fornecedor) objeto;
            %>
            <div id="nomeSection">
                <input type="text" name="nome" id="nome" class='campos' required placeholder="Nome" value="<%=p.getNome()%>">
            </div>
            <div id="sobrenomeSection">               
                <input type="text" name="sobrenome" id="sobrenome" class='campos' required placeholder="E-mail" value="<%=p.getEmail()%>">
            </div>
            <div id="cpfSection">
                <input type="text" name="cpf" id="cpf" class='campos' required maxlength='11' placeholder="CNPJ" value='<%=p.getCnpj() %>'>
            </div>
            <div id="telefoneSection">
            	    <select name="telefone" id="telefone" class='campos'>
                        <option value="Entrega Fixa" <%if ((telefone.toLowerCase()).equals("entrega fixa")){%>selected<%} %>>Preço Fixo</option>
                        <option value="Entrega Por Km" <%if ((telefone.toLowerCase()).equals("entrega por km")){%>selected<%} %>>Preço por km</option>
                    </select>
            </div>

            <div id="datasNascSection">
                <input type="number" name="datasNasc" id="datasNasc" class='campos' required placeholder="Valor entrega" value=<%=p.getPrecoEntrega() %>>

            </div>

            <div id="emailSection">
            	<select name="email" id="email" class='campos'>                        
                        <option value="Restaurante" <%if ((email.toLowerCase()).equals("restaurante")){%>selected<%} %>>Restaurante</option>                     
                        <option value="Mercado" <%if ((email.toLowerCase()).equals("mercado")){%>selected<%} %>>Mercado</option>
                        <option value="Bebidas" <%if ((email.toLowerCase()).equals("bebidas")){%>selected<%} %>>Bebidas</option>
                        <option value="Frutas" <%if ((email.toLowerCase()).equals("frutas")){%>selected<%} %>>Frutas</option>                   
                    </select>            	
            </div>
            <%} %>

           	<button id="apagarConta" onclick="abrirApagarConta()">Apagar Conta</button>

            <button id="atualizar" onclick="solicitarSenha()">
                Atualizar dados
            </button>
            
            <button id="alterarSenha" onclick="alterarSenha()">Trocar Senha</button> 
            <input type="file" id="file" name="file" onchange="uploadFile()" value="olá" style="visibility: hidden;" accept=".png">
        </div>
    </div>
    <div id="blur">
        <div id="fundoConfirmacao">
            <h1 id="tituloConfirmacao">Confirme sua senha</h1>
            <div id="senha">
                <input type="text" name="senhaInput" id="senhaInput"required placeholder="Antiga Senha">
            </div>
            <div id="senhaNova">
                <input type="text" name="senhaNovaInput" id="senhaNovaInput" required placeholder="Nova Senha">
            </div>
            <div id="confirmarSenha">
                <input type="text" name="confirmarSenhaInput" id="confirmarSenhaInput" required placeholder="Confirmar Senha">
            </div>
            <div id="buttons">
                <button id="fechar" onclick="fecharPopUp()">Fechar</button>
                <button id="continuar">continuar</button>
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
    </div>



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
    <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script></body>

</body>
</html>