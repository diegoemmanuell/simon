package controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import lombok.Getter;
import lombok.Setter;
import model.Disciplina;
import model.Serie;
import model.Turma;
import util.jsf.FacesUtil;
import dao.DisciplinaDao;
import dao.TurmaDao;

@ManagedBean
public class DisciplinaController {
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private @Getter @Setter DisciplinaDao disciplinaDao;
	private @Getter @Setter Disciplina disciplina;
	private @Getter @Setter List<SelectItem> listaSeries;
	private @Getter @Setter List<Serie> series;
	private @Getter @Setter int serie;
	private @Getter @Setter String serieSelecionada;
	private @Getter @Setter List<Disciplina> disciplinas;
	private @Getter @Setter boolean exibeDataTable;
	
	public DisciplinaController() {
		disciplinaDao = new DisciplinaDao();
		disciplina = new Disciplina();
		listaSeries = disciplinaDao.listarSeries();
		disciplina.setSerie(new Serie());
		exibeDataTable = false;
	}
	public void cadastrarDisciplina(){
		if(serieSelecionada != null && !serieSelecionada.equals("")){
			disciplina.getSerie().setId(Integer.parseInt(serieSelecionada));
			if(disciplinaDao.insert(disciplina)){
				FacesUtil.setMessageSucesso("SUCESSO", "Disciplina adicionada!");
			}else{
				FacesUtil.setMessageSucesso("ERRO", "Não foi possível registrar a disciplina!");
			}
		}else{
			FacesUtil.setMessageSucesso("ADVERTÊNCIA", "Selecione uma série!");
		}
	}
	
	public void buscarDisciplinas(){
		serie = Integer.parseInt(serieSelecionada);
		disciplina.getSerie().setId(serie);
		if(serie == 0){
			disciplinas = disciplinaDao.listaTodasAsDisciplinas();
		}else{
			disciplinas = disciplinaDao.listaDisciplinaPorSerie(disciplina);
		}
		if(disciplinas != null && disciplinas.size()>0){
			setExibeDataTable(true);
		}else{
			FacesUtil.setMessageSucesso("ERRO", "Nenhuma disciplina foi encontrada!");
		}
	}
	
	
}
