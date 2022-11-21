package servico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import entidade.Fornecedor;
import entidade.Pessoa;
import entidade.Produto;
import entidade.Token;
import interfaces.IFornecedorServico;
import interfaces.IPessoaServico;
import interfaces.IProdutoServico;
import interfaces.ITokenRepositorio;
import interfaces.ITokenServico;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TokenRepositorio;

public class TokenServico implements ITokenServico {
	ITokenRepositorio repositorio = new TokenRepositorio();
	IPessoaServico pessoa = new PessoaServico();
	IFornecedorServico forncedor = new FornecedorServico();
	IProdutoServico produto = new ProdutoServico();

	@Override 
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
			Cookie cookieType = null;

			if (objeto instanceof Pessoa) {
				Pessoa pessoa = (Pessoa) objeto;
				token = new Token(pessoa, tokenNew, tokenNew, formatter.format(dataAtual.getTime()), formatter.format(dataExpiracao.getTime()), true);
				cookieToken = new Cookie("token", token.getToken());
				cookieId = new Cookie("id",Integer.toString(token.getUsuario().getId()));
				cookieType = new Cookie("type", "us");
				repositorio.adicionarToken(token);
			}
			else if (objeto instanceof Fornecedor) {
				Fornecedor fornecedor = (Fornecedor) objeto;
				token = new Token(fornecedor, tokenNew, tokenNew, formatter.format(dataAtual.getTime()), formatter.format(dataExpiracao.getTime()), true);
				cookieToken = new Cookie("token", token.getToken());
				cookieId = new Cookie("id",Integer.toString(token.getFornecedor().getId()));
				cookieType = new Cookie("type", "fo");
				repositorio.adicionarTokenFornecedor(token);
			}
			response.addCookie(cookieToken);
			response.addCookie(cookieId);
			response.addCookie(cookieType);

			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public boolean autenticarLogin(HttpServletRequest request) {
	    try {
	        String token = "";
	        String typeUser = "";
	        Cookie[] cookies = request.getCookies();
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("token")){
	            	
	                token = cookie.getValue();
	                }
	            if (cookie.getName().equals("type")) {
	            	typeUser = cookie.getValue();
	            	}
	           	}
	        
	        
	        Token tk = null;
	        if (typeUser.equals("us")) {
		        tk = repositorio.autenticarToken(token);
	        }else if(typeUser.equals("fo")) {
	        	tk = repositorio.autenticarTokenFornecedor(token);
	        }
	        
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
	
	@Override
	public void zerarCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String type = "";
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("type")) {
				type = cookie.getValue();
				break;
			}
		}
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("id")){
				if (type == "us") repositorio.desativarToken(Integer.parseInt(cookie.getValue()));
				else if (type == "fo") repositorio.desativarTokenFornecedor(Integer.parseInt(cookie.getValue()));
			}
			if (!cookie.getName().equals("JSESSIONID")) {
				System.out.println(cookie.getName());
				System.out.println(cookie.getValue());
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}
	
	public Object pegarUsuario(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();	
		String type = "";
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("type")) {
				type = cookie.getValue();
				break;
			}
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("id")){			
				if (type.equals("us")) {
					return pessoa.verificarId(Integer.parseInt(cookie.getValue()));
				}
				else if (type.equals("fo")) {
					return forncedor.buscarPorId(Integer.parseInt(cookie.getValue()));
				}

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

	public String pegarTipoUsuario(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("type")){
                return cookie.getValue();
            }
        }
        return null;
	}
	
	public void adicionarCarrinho(String id, String quantidade, HttpServletResponse response) {
		Cookie cookie = new Cookie(id, quantidade);
		response.addCookie(cookie);
	}
	
	public Map<Produto, Integer> pegarCarrinho(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		Map<Produto, Integer> carrinho = new HashMap<Produto, Integer>();
		for(Cookie cookie : cookies) {	
			if (!cookie.getName().equals("type") && !cookie.getName().equals("id") && !cookie.getName().equals("token") && !cookie.getName().equals("JSESSIONID")){
				carrinho.put(produto.buscarProdutoPorId(Integer.parseInt(cookie.getName())), Integer.parseInt(cookie.getValue()));
			}
		}	
		return carrinho;
	}
	
	public boolean adicionarProdutoCarrinho(HttpServletResponse response, int id, int quantidade) {
		try {
			Cookie cookie = new Cookie(Integer.toString(id) ,Integer.toString(quantidade));
			response.addCookie(cookie);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean retirarProdutoCarrinho(HttpServletRequest request, HttpServletResponse response, int id, int quantidade) {
		try {
			Cookie[] cookies = request.getCookies();

			for (Cookie cookie: cookies) {

				if (cookie.getName().equals(Integer.toString(id)) && cookie.getValue().equals(Integer.toString(quantidade))){
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					listarCookies(request);
					break;
					
				}
			}			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void listarCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		for (Cookie cookie: cookies) {
		}
		
	}
	
	
}
