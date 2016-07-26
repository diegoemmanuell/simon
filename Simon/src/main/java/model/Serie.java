package model;

import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SessionScoped
@SequenceGenerator(name="SQ_SERIE", sequenceName="SQ_SERIE", allocationSize=1, initialValue=1)
public class Serie {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SQ_SERIE")
	private int id;
	private String nome;
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
	
	
	
}
