package controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Base64;

import entidade.Fornecedor;
import entidade.Pessoa;
import interfaces.IFornecedorServico;
import interfaces.IPessoaServico;
import interfaces.ITokenServico;
import servico.FornecedorServico;
import servico.PessoaServico;
import servico.TokenServico;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;



    public Controller() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	

	}
	


}
