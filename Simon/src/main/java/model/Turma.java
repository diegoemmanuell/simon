package model;

import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@SessionScoped
@SequenceGenerator(name="SQ_TURMA", sequenceName="SQ_TURMA", allocationSize=1, initialValue=1)
public class Turma {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SQ_TURMA")
	private @Getter @Setter int id;
	private @Getter @Setter String nome;
	@ManyToOne
	private @Getter @Setter Serie serie;
	
	
}
