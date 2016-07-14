package model;

import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SessionScoped
@SequenceGenerator(name="SQ_USUARIO", sequenceName="SQ_USUARIO", allocationSize=1, initialValue=1)
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SQ_USUARIO")
	private Long id;
	private String nome;
	@Column(unique = true)
	private String login;
	private String senha;
	private String dtInclusao;
	private int perfil;
	private String email;
	private String matricula;
	private String cpf;
	private int turma;
	private boolean statusMatricula;
	
	
	public boolean isStatusMatricula() {
		return statusMatricula;
	}
	public void setStatusMatricula(boolean statusMatricula) {
		this.statusMatricula = statusMatricula;
	}
	public int getTurma() {
		return turma;
	}
	public void setTurma(int turma) {
		this.turma = turma;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getDtInclusao() {
		return dtInclusao;
	}
	public void setDtInclusao(String dtInclusao) {
		this.dtInclusao = dtInclusao;
	}
	public int getPerfil() {
		return perfil;
	}
	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}
	
		
	
	
}
