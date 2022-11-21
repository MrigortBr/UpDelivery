<%@page import="java.text.DecimalFormat"%>
<%@page import="servico.FornecedorServico"%>
<%@page import="interfaces.IFornecedorServico"%>
<%@page import="java.io.File"%>
<%@page import="jakarta.servlet.ServletContext"%>
<%@page import="entidade.Fornecedor"%>
<%@page import="entidade.Pessoa"%>
<%@page import="servico.TokenServico"%>
<%@page import="interfaces.ITokenServico"%>
<%@page import="servico.ProdutoServico"%>
<%@page import="interfaces.IProdutoServico"%>
<%@page import="entidade.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <%
	String titulo = "";
	ArrayList<Produto> produtos = null;
	IProdutoServico prod = new ProdutoServico();
	IFornecedorServico fornecedorServico = new FornecedorServico();


    %>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cardapio</title>
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/cardapio.css">
   	<link rel="stylesheet" href="styles/mensagemErro.css">
    <script src="scripts/cardapio.js"></script>
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
    <main>
    	<%
    			DecimalFormat formatter = new DecimalFormat("#.00");

    	    	ArrayList<Produto> cardapio = fornecedorServico.listarCardapio(Integer.parseInt(token.pegarId(request)));
    	%>
        <div class="fundoProdutos">
            <h1>Produtos Criados</h1>
            <%if(!cardapio.isEmpty()){
            	for (Produto produto: cardapio){
		%>
            <div class="produto" onkeydown="alterarDados('<%=produto.getId()%>')" id="produto<%=produto.getId()%>">
                <img src="produtos/<%=produto.getNome().replace(" ", "")+ produto.getId() %>.png" alt="" class="fotoProduto" id="fotoProduto<%=produto.getId()%>" onclick="alterarFotoProduto('<%=produto.getId()%>')">
                <div class="nome">
                    <label for="nomeProduto">Nome:</label><br>
                    <input type="text" name="nomeProduto" class="nomeProduto" id="nomeProduto<%=produto.getId()%>" value="<%=produto.getNome()%>"><br>
                    <label for="descricaoProduto">Descrição:</label><br>
                    <textarea type="text" name="descricaoProduto" class="descricaoProduto" id="descricaoProduto<%=produto.getId()%>"><%=produto.getDescricao()%></textarea>
                </div>
                <div class="preco">
                    <label for="tipoProduto">Tipo:</label>
                    <input type="text" name="tipoProduto" class="tipoProduto" id="tipoProduto<%=produto.getId()%>" value="<%=produto.getTipo()%>"><br>

                    <label for="precoProduto">Preço:</label>

                    <input type="text" name="precoProduto" class="precoProduto" id="precoProduto<%=produto.getId()%>" value="<%=formatter.format(produto.getPreco())%>"><br>
                
                    <label for="desconto">Desconto:</label>

                    <input type="text" name="desconto" class="desconto" id="desconto<%=produto.getId()%>" value="<%=(int)(produto.getDesconto()*100)%>%">

                </div>
                <div class="options">
                    <img src="files/check-circle.svg" alt="" class="salvar" id="salvar<%=produto.getId()%>" style="visibility: hidden;" onclick="atualizar('<%=produto.getId()%>')">
                    <img src="files/delete.svg" alt="" class="deletar" id="deletar<%=produto.getId()%>" onclick="deletar('<%=produto.getId()%>')">
                    <input type="file" style="visibility: hidden; position: absolute;" id="file<%=produto.getId()%>" onchange="uploadFile('<%=produto.getId()%>')">

                </div>
            </div>   
            <%
            }	
            }else{ %>
            <h2>Sem Produtos registrados</h2>
            <%}; %>
            
            <h1>Adicionar Novos Produtos</h1>
            <div class="produto">
                <img src="produtos/image.png" alt="" class="fotoProduto" id="fotoProduto0" onclick="alterarFotoProduto('0')">
                <div class="nome">
                    <label for="nomeProduto">Nome:</label><br>
                    <input type="text" name="nomeProduto" class="nomeProduto" id="nomeProduto0"><br>
                    <label for="descricaoProduto">Descrição:</label><br>
                    <textarea type="text" name="descricaoProduto" class="descricaoProduto" id="descricaoProduto0"></textarea>
                </div>
                <div class="preco">
                    <label for="tipoProduto">Tipo:</label>
                    <input type="text" name="tipoProduto" class="tipoProduto" id="tipoProduto0"><br>

                    <label for="precoProduto">Preço:</label>

                    <input type="text" name="precoProduto" class="precoProduto" id="precoProduto0"><br>
                
                    <label for="desconto">Desconto:</label>

                    <input type="text" name="desconto" class="desconto" id="desconto0">

                </div>
                <div class="options">
                    <img src="files/check-circle.svg" alt="" class="salvar" id="salvar0" onclick="adicionar('0')">
                    <img src="files/delete.svg" alt="" class="deletar" id="deletar0" onclick="deletar('0')">
                    <input type="file" style="visibility: hidden; position: absolute;" id="file0" onchange="uploadFile('0')">

                </div>
            </div>   
        </div>    
    </main>
    
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
	<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script></body>

</body>
</html>