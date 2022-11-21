<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="interfaces.ITokenServico" %>
<%@ page import="servico.TokenServico" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/login.css">
    <link rel="stylesheet" href="styles/mensagemErro.css">
    <script src="scripts/login.js"></script>
    <script src="scripts/scripts.js"></script>


    <title>Pagina Login</title>
</head>
<body>
	<%
		ITokenServico token = new TokenServico();
		if (token.autenticarLogin(request) == true){
			response.sendRedirect("index.jsp");
		}
	%>
    <div class="fundo">
        <span class="textos">
            <h1 class="logo"><a href="index.jsp">Logo</a></h1>
            <h1 class="titulo">Bem Vindo<br>ao melhor app de delivery</h1>
        </span>

        <img src="images/Saly-1.png" alt="" class="picture-fundo">
    </div>
    <div class="informacoes-fundo">
        <div class="grid1">
            <span class="bemvindo">Bem vindo ao <b>Up Delivery</b></span>
        </div>
        <section class="grid2">
            <section class="row">
                <span class="entre">Entrar</span>
                <span class="registrar"> <a href="registrar.jsp"> Não tem conta? clique aqui</a></span>
            </section>
            <div class="widgets">
                <picture>
                    <img src="assets/google.png" alt="" class="metodo-login">
                </picture>
                <picture>
                    <img src="assets/facebbok.png" alt="" class="metodo-login">
                </picture>
                <picture>
                    <img src="assets/apple.png" alt="" class="metodo-login">
                </picture>
            </div>
            <section class="input">
                <input type="text" name="email" id="email" class='campos' required>
                <label for="email" class="input-label">Endereço de Email *</label>
            </section>
            <section class="input">
                <input type="password" name="password" id="senha" class='campos' required>
                <label for="name" class="input-label" style="margin-left: -25%;">Digite a Senha *</label>
            </section>

            <input type="button" value="Entrar" id="logar" onclick="logar()">
        </section>
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
	<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script></body>
</html>