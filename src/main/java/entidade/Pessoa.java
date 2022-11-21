package entidade;

public class Pessoa {
private int id;
private String nome;
private String sobrenome;
private String email;
private String cpf;
private String telefone;
private String dataNascimento;
private String senha;

public Pessoa(int id, String nome, String sobrenome, String email, String cpf, String telefone, String dataNascimento,
		 String senha) {
	super();
	this.id = id;
	this.nome = nome;
	this.sobrenome = sobrenome;
	this.email = email;
	this.cpf = cpf;
	this.telefone = telefone;
	this.dataNascimento = dataNascimento;
	this.senha = senha;
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
public String getSobrenome() {
	return sobrenome;
}
public void setSobrenome(String sobrenome) {
	this.sobrenome = sobrenome;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getCpf() {
	return cpf;
}
public void setCpf(String cpf) {
	this.cpf = cpf;
}
public String getTelefone() {
	return telefone;
}
public void setTelefone(String telefone) {
	this.telefone = telefone;
}
public String getDataNascimento() {
	return dataNascimento;
}
public void setDataNascimento(String dataNascimento) {
	this.dataNascimento = dataNascimento;
}
public String getSenha() {
	return senha;
}
public void setSenha(String senha) {
	this.senha = senha;
}}
