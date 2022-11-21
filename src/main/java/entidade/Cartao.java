package entidade;

public class Cartao {
	private int id;
	private int idPessoa;
	private String apelido;
	private String cartao;
	private String cvv;
	private String validade;
	private String tipo;
	private String bandeira;
	
	public Cartao(int idPessoa, String apelido, String cartao, String cvv, String validade, String tipo, String bandeira, int id) {
		super();
		this.id = id;
		this.idPessoa = idPessoa;
		this.apelido = apelido;
		this.cartao = cartao;
		this.validade = validade;
		this.cvv = cvv;
		this.tipo = tipo;
		this.bandeira = bandeira;
	}
	
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getCartao() {
		return cartao;
	}
	public void setCartao(String cartao) {
		this.cartao = cartao;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}	
}
