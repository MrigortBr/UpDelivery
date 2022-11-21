package entidade;

public class Token{
	private int idUsuario;
	private Pessoa usuario;
	private Fornecedor fornecedor;
	private String token;
	private String tokenSecundario;
	private String dataCriacao;
	private String dataValidade;
	private Boolean working;
	
	public Token(Pessoa usuario, String token, String tokenSecundario, String dataCriacao, String dataValidade, Boolean working) {
		super();
		this.usuario = usuario;
		this.token = token;
		this.tokenSecundario = tokenSecundario;
		this.dataCriacao = dataCriacao;
		this.dataValidade = dataValidade;
		this.working = working;
	}
	
	public Token(Fornecedor fornecedor, String token, String tokenSecundario, String dataCriacao, String dataValidade, Boolean working) {
		super();
		this.fornecedor = fornecedor;
		this.token = token;
		this.tokenSecundario = tokenSecundario;
		this.dataCriacao = dataCriacao;
		this.dataValidade = dataValidade;
		this.working = working;
	}
	
	public Token(int idUsuario, String token, String tokenSecundario, String dataCriacao, String dataValidade, Boolean working) {
		super();
		this.setIdUsuario(idUsuario);
		this.token = token;
		this.tokenSecundario = tokenSecundario;
		this.dataCriacao = dataCriacao;
		this.dataValidade = dataValidade;
		this.working = working;
	}
	
	
	
	public Pessoa getUsuario() {
		return usuario;
	}
	public void setUsuario(Pessoa Usuario) {
		this.usuario = Usuario;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenSecundario() {
		return tokenSecundario;
	}
	public void setTokenSecundario(String tokenSecundario) {
		this.tokenSecundario = tokenSecundario;
	}
	public String getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Boolean getWorking() {
		return working;
	}

	public void setWorking(Boolean working) {
		this.working = working;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
	
}

