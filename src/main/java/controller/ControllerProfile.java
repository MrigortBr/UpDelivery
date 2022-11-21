package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import entidade.Fornecedor;
import entidade.Pessoa;
import interfaces.IFornecedorServico;
import interfaces.IPessoaServico;
import interfaces.ITokenServico;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servico.FornecedorServico;
import servico.PessoaServico;
import servico.TokenServico;

public class ControllerProfile extends HttpServlet{
	private static final long serialVersionUID = 4L;
	private IPessoaServico pessoa = new PessoaServico();
	private IFornecedorServico fornecedor = new FornecedorServico();
	private ITokenServico token = new TokenServico();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeRequest = request.getParameter("method");
		switch (typeRequest) {
		case "validarSenha":
		    responderValidarSenha(request.getParameter("senha"), request, response);
		    break;
        case "atualizarUsuario":
            responderAtualizarUsuario(request.getParameter("nome"), request.getParameter("sobrenome"), request.getParameter("email"), request.getParameter("cpf"), request.getParameter("telefone"), request.getParameter("dataNasc"), response, request);
            break;
        case "apagarConta":
            responderApagarUsuario(response, request);
            break;
        case "alterarSenha":
            responderAlterarSenha(request.getParameter("novaSenha"), response, request);
            break;
		}
	}
	
	
	private void responderValidarSenha(String Senha, HttpServletRequest request, HttpServletResponse response) throws IOException {	   
	    String tipoCliente = token.pegarTipoUsuario(request);
		if (tipoCliente.equals("fo")) {
			if (fornecedor.validarSenha(Senha, token.pegarToken(request)) == true) {
			    response.getWriter().write("true");
			}else {
			    response.getWriter().write("false");
			}
					
		}else if (tipoCliente.equals("us")) {
			if (pessoa.validarSenha(Senha, token.pegarToken(request)) == true) {
		        response.getWriter().write("true");
		    }else {
		        response.getWriter().write("false");
		    }
		}
		
	    
	    
		
	}
	
    private void responderAtualizarUsuario(String nome, String sobrenome, String email, String cpf, String telefone, String dataNascimento, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {    	
    	String tipoCliente = token.pegarTipoUsuario(request);
		if (tipoCliente.equals("fo")) {
	    	if (fornecedor.atualizar(Integer.parseInt(token.pegarId(request)), nome, cpf, telefone, Float.parseFloat(dataNascimento), sobrenome, email) == true) {
	            response.getWriter().write("true");
	        }else {
	            response.getWriter().write("false");
	        }
		}else if (tipoCliente.equals("us")) {
	    	if (pessoa.atualizar(nome, sobrenome, email, cpf, telefone, dataNascimento, token.pegarId(request)) == true) {
	            response.getWriter().write("true");
	        }else {
	            response.getWriter().write("false");
	        }
		}

    }
	
	private void responderApagarUsuario(HttpServletResponse response, HttpServletRequest request) throws IOException {
    	String tipoCliente = token.pegarTipoUsuario(request);
		int id = Integer.parseInt(token.pegarId(request)); 

		if (tipoCliente.equals("fo")) {
	    	if (fornecedor.deletar(id) == true) {
	            token.zerarCookie(request, response);
	            response.getWriter().write("true");
	        }else {
	            response.getWriter().write("false");
	        }
		}else if (tipoCliente.equals("us")) {
		    if (pessoa.deletar(id) == true) {
	            token.zerarCookie(request, response);
	            response.getWriter().write("true");
	        }else {
	            response.getWriter().write("false");
	        }
		}
	}
	private void responderAlterarSenha(String senhaNova, HttpServletResponse response,HttpServletRequest request) throws IOException {
    	String tipoCliente = token.pegarTipoUsuario(request);
		int id = Integer.parseInt(token.pegarId(request)); 
		
		if (tipoCliente.equals("fo")) {
	    	if (fornecedor.aletarSenha(id, senhaNova) == true) {
	            response.getWriter().write("true");
	        }else {
	            response.getWriter().write("false");
	        }
		}else if (tipoCliente.equals("us")) {
		    if (pessoa.aletarSenha(id, senhaNova) == true) {
	            response.getWriter().write("true");
	        }else {
	            response.getWriter().write("false");
	        }
		}
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String typeRequest = request.getParameter("method");
	      switch (typeRequest) {
	          case "atualizarImagem":
	                atualizarImagem(request, response);
	                break;
	          
	      }
	}

	private void atualizarImagem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String fileUpload = request.getParameter("base64");
	    if (fileUpload != null) {
             String fileUpload2 = fileUpload.replace("data:image/png;base64,", "");
             ServletContext ctx = getServletContext();
             String path = ctx.getRealPath( "/" );
             String pathType = "";
             
             if (token.pegarTipoUsuario(request).equals("fo")) {
            	 pathType = "fornecedor/";
                 Fornecedor fornecedor = (Fornecedor) token.pegarUsuario(request);
                 File diretorio2 = new File(path+pathType+fornecedor.getId()+fornecedor.getNome().replaceAll(" ", "_")+".png");
                 if (diretorio2.exists() ) {
                 	diretorio2.delete();
                 } 
                 try(FileOutputStream file = new FileOutputStream(new File(path+pathType+fornecedor.getId()+fornecedor.getNome().replaceAll(" ", "_")+".png"))) {
                     file.write(Base64.getDecoder().decode(fileUpload2));
                     response.getWriter().write("true");
                 }catch (Exception e) {
                     response.getWriter().write("false");
                 }

             }
             else if (token.pegarTipoUsuario(request).equals("us")) {
            	 pathType = "users/";
                 Pessoa usuario = (Pessoa) token.pegarUsuario(request);
                 File diretorio2 = new File(path+pathType+usuario.getId()+usuario.getNome().replaceAll(" ", "_")+".png");
                 if (diretorio2.exists() ) {
                 	diretorio2.delete();
                 }
                 try(FileOutputStream file = new FileOutputStream(new File(path+pathType+usuario.getId()+usuario.getNome().replaceAll(" ", "_")+".png"))) {
                     file.write(Base64.getDecoder().decode(fileUpload2));
                     response.getWriter().write("true");
                 }catch (Exception e) {
                     response.getWriter().write("false");
                 }

             }else {
            	 System.out.println("Nao correspondido");
                 response.getWriter().write("false");
             }
         }else {
             response.getWriter().write("false");
         }
	}
}
