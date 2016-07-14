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
@SequenceGenerator(name="SQ_QUESTAO", sequenceName="SQ_QUESTAO", allocationSize=1, initialValue=1)
public class Questao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SQ_QUESTAO")
	private @Getter @Setter int id;
	private @Getter @Setter String enunciado;
	private @Getter @Setter String alternativaUm;
	private @Getter @Setter String alternativaDois;
	private @Getter @Setter String alternativaTres;
	private @Getter @Setter String alternativaQuatro;
	private @Getter @Setter String alternativaCinco;
	private @Getter @Setter String alternativaCorreta;
	@ManyToOne
	private @Getter @Setter Disciplina disciplina;
	@ManyToOne
	private @Getter @Setter Serie serie;
	
}
