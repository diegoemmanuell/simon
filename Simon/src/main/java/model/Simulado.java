package model;

import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@SessionScoped
public class Simulado {
	@Id
	@GeneratedValue
	private @Getter @Setter int id;
	private @Getter @Setter List<Questao> questoes;
}
