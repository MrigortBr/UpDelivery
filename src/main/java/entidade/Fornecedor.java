package entidade;

public class Fornecedor {
	private int id;
	private String nome;
	private String cnpj;
	private String tipoEntrega;
	private float precoEntrega;
	private String email;
	private String senha;
	private String tipo;
	private float avaliacao;
	
	public Fornecedor(int id, String nome, String cnpj, String tipoEntrega, float precoEntrega, String email,
			String senha, String tipo, float avaliacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.tipoEntrega = tipoEntrega;
		this.precoEntrega = precoEntrega;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
		this.avaliacao = avaliacao;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getTipoEntrega() {
		return tipoEntrega;
	}
	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}
	public float getPrecoEntrega() {
		return precoEntrega;
	}
	public void setPrecoEntrega(float precoEntrega) {
		this.precoEntrega = precoEntrega;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public float getAvaliacao() {
		return avaliacao;
	}



	public void setAvaliacao(float avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	
}
