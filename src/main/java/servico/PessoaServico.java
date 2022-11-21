package servico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entidade.Pessoa;
import interfaces.IPessoaServico;
import interfaces.IPessoaRepositorio;
import model.PessoaRepositorio;

public class PessoaServico implements IPessoaServico {
	IPessoaRepositorio repositorio = new PessoaRepositorio();
    
	@Override
	public Pessoa verificarLogin(String login, String senha) {
		//Valida sem existe pessoa com o login e senha enviados
		Pessoa pessoa = repositorio.buscarPorLogin(login, senha);
		
		//Retorna a pessoa caso nao tenha achado retornara pessoa como null
		return pessoa;
	}
		
	public Pessoa verificarId(int id) {
		return repositorio.buscarPorId(id);
	}

    @Override
    public boolean criarRegistro(String nome, String sobrenome, String email, String cpf, String telefone,
            String dataNascimento, String senha) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            @SuppressWarnings("unused")
			Date data = formatter.parse(dataNascimento);
        } catch (ParseException e) {
            return false;
        }
        repositorio.registrar(nome, sobrenome, email, cpf, telefone, dataNascimento, senha);
        
        return true;
    }

    @Override
    public String vericiarRegistro(String email, String cpf, String telefone) {
        ArrayList<String> resposta = repositorio.verificarRegistro(email, cpf, telefone);
        String resultado = "";
        for (String string : resposta) {
            if (string.equals("email")){ resultado += ",email";}
            else if (string.equals("cpf")) {resultado += ",cpf";}
            else if (string.equals("telefone")) {resultado += ",telefone";}
        }

        return resultado;
    }

    @Override
    public boolean validarSenha(String senha, String session) {
        return repositorio.validarSenha(senha, session);
    }
    
    public boolean atualizar(String nome, String sobrenome, String email, String cpf, String telefone, String dataNascimento, String id) {
        return repositorio.atualizar(id, nome, sobrenome, email, cpf, telefone, dataNascimento);
    }

    @Override
    public boolean deletar(int id) {      
        return repositorio.deletar(id);
    }

    @Override
    public boolean aletarSenha(int id, String novaSenha) {
        return repositorio.aletarSenha(id, novaSenha);
    }



     
}
