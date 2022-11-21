package servico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import entidade.Fornecedor;
import entidade.Pessoa;
import entidade.Token;
import interfaces.IPessoaServico;
import interfaces.ITokenRepositorio;
import interfaces.ITokenServico;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TokenRepositorio;

public class TokenServico2 {
	ITokenRepositorio repositorio = new TokenRepositorio();
	IPessoaServico pessoa = new PessoaServico();

	public boolean criarToken(Object objeto, HttpServletResponse response) {
		if (objeto != null) {
			//Configurações do token e instancia da biblioteca random.
			String patternToken = "abcdefghijklmnopqrstuvxwyzABCDEFGHIJKLMNOPQRSTUVXWYZ123456789&@";
			String tokenNew = "";
			Random random = new Random();
			
			//Data de criação e data de valdiade.
			Calendar dataAtual = Calendar.getInstance();
			Calendar dataExpiracao = Calendar.getInstance();
			dataExpiracao.add(Calendar.DAY_OF_MONTH, 30);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			//Cria um token de maneira unica
			while (!(repositorio.autenticarToken(tokenNew) == null) | tokenNew.equals("") != false) {
				for (int cont = 0; cont < 20; cont++) {
					int rand = random.nextInt(patternToken.length());
					tokenNew += patternToken.charAt(rand);
				}				
			}
			Token token = null;
			Cookie cookieToken = null;
			Cookie cookieId = null;

			if (objeto instanceof Pessoa) {
				Pessoa pessoa = (Pessoa) objeto;
				token = new Token(pessoa, tokenNew, tokenNew, formatter.format(dataAtual.getTime()), formatter.format(dataExpiracao.getTime()), true);
				cookieToken = new Cookie("token", token.getToken());
				cookieId = new Cookie("id",Integer.toString(token.getUsuario().getId()));
			}
			else if (objeto instanceof Fornecedor) {
				Fornecedor fornecedor = (Fornecedor) objeto;
				token = new Token(fornecedor, tokenNew, tokenNew, formatter.format(dataAtual.getTime()), formatter.format(dataExpiracao.getTime()), true);
				cookieToken = new Cookie("token", token.getToken());
				cookieId = new Cookie("id",Integer.toString(token.getFornecedor().getId()));
			}
			response.addCookie(cookieToken);
			response.addCookie(cookieId);
			repositorio.adicionarToken(token);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean autenticarLogin(HttpServletRequest request) {
	    try {
	        String token = "";
	        Cookie[] cookies = request.getCookies();
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("token")){
	                token = cookie.getValue(); {
	                }
	            }
	        }
	        
	        Token tk = repositorio.autenticarToken(token);
	        
	        if (tk != null) {
	            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	            String  str1 = tk.getDataValidade();
	            Date date1 = null;
	            try {
	                date1 = formatter.parse(str1);
	            } catch (ParseException e1) {
	                e1.printStackTrace();
	            }
	            Date  date2 = new Date(System.currentTimeMillis());
	            try {
	                date1 = formatter.parse(str1);  
	            } catch (Exception e) {
	            }
	            
	            if (date1.before(date2) == true) {
	                return false;   
	            }else {
	                return true;
	            }
	        }else {
	            return false;
	        }
        } catch (Exception e) {
            return false;
        }
		
		
	}
	
	public void zerarCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("id")){
				repositorio.desativarToken(Integer.parseInt(cookie.getValue()));
			}
			if (!cookie.getName().equals("JSESSIONID")) {
				cookie.setValue(null);
				response.addCookie(cookie);
			}
		}
	}
	
	public Pessoa pegarUsuario(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();	
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("id")){
				return pessoa.verificarId(Integer.parseInt(cookie.getValue()));
			}
		}
		return null;
	}
	
	public String pegarToken(HttpServletRequest request){
	    Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                return cookie.getValue();
            }
        }
        return null;
	}
	
	public String pegarId(HttpServletRequest request){
	        Cookie[] cookies = request.getCookies();
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("id")){
	                return cookie.getValue();
	            }
	        }
	        return null;
	    }

	public boolean criarTokenFornecedor(Fornecedor fornecedor, HttpServletResponse response) {
		if (fornecedor != null) {
			//Configurações do token e instancia da biblioteca random.
			String patternToken = "abcdefghijklmnopqrstuvxwyzABCDEFGHIJKLMNOPQRSTUVXWYZ123456789&@";
			String tokenNew = "";
			Random random = new Random();
			
			//Data de criação e data de valdiade.
			Calendar dataAtual = Calendar.getInstance();
			Calendar dataExpiracao = Calendar.getInstance();
			dataExpiracao.add(Calendar.DAY_OF_MONTH, 30);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			//Cria um token de maneira unica
			while (!(repositorio.autenticarToken(tokenNew) == null) | tokenNew.equals("") != false) {
				for (int cont = 0; cont < 20; cont++) {
					int rand = random.nextInt(patternToken.length());
					tokenNew += patternToken.charAt(rand);
				}				
			}

			Token token = new Token(fornecedor, tokenNew, tokenNew, formatter.format(dataAtual.getTime()), formatter.format(dataExpiracao.getTime()), true);
						
			Cookie cookieToken = new Cookie("token", token.getToken());
			Cookie cookieId = new Cookie("id",Integer.toString(token.getUsuario().getId()));
			
			response.addCookie(cookieToken);
			response.addCookie(cookieId);
			repositorio.adicionarToken(token);
			return true;
		}else {
			return false;
		}
	}	
}
