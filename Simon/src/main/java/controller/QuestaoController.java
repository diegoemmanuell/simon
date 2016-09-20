package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import lombok.Getter;
import lombok.Setter;
import model.Disciplina;
import model.Questao;
import model.Serie;
import util.jsf.FacesUtil;
import dao.DisciplinaDao;
import dao.QuestaoDao;

@ManagedBean
public class QuestaoController {
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private @Getter @Setter DisciplinaDao disciplinaDao;
	private @Getter @Setter QuestaoDao questaoDao;
	private @Getter @Setter Questao questao;
	private @Getter @Setter List<SelectItem> listaSeries;
	private @Getter @Setter List<SelectItem> listaDisciplinas;
	private @Getter @Setter List<Serie> series;
	private @Getter @Setter String serieSelecionada;
	private @Getter @Setter String disciplinaSelecionada;
	private @Getter @Setter List<Disciplina> disciplinas;
	private @Getter @Setter List<Questao> questoes;
	private @Getter @Setter boolean exibeDataTable;
	private @Getter @Setter Questao questaoSelecionada;
	
	public QuestaoController() {
		disciplinaDao = new DisciplinaDao();
		questaoDao = new QuestaoDao();
		questao = new Questao();
		listaSeries = questaoDao.listarSeries();
		questao.setSerie(new Serie());
		questao.setDisciplina(new Disciplina());
		exibeDataTable = false;
		listaDisciplinas = questaoDao.listarDisciplinasTodasAsSeries();

	}
	public void cadastrarQuestao(){
		if(serieSelecionada != null && !serieSelecionada.equals("")){
			if(disciplinaSelecionada != null && !disciplinaSelecionada.equals("")){
				questao.getSerie().setId(Integer.parseInt(serieSelecionada));
				questao.getDisciplina().setId(Integer.parseInt(disciplinaSelecionada));
				if(questaoDao.insert(questao)){
					FacesUtil.setMessageSucesso("SUCESSO", "Questão adicionada!");
				}else{
					FacesUtil.setMessageError("ERRO", "Não foi possível registrar a questão!");
				}
			}else{
				FacesUtil.setMessageAdvertencia("ADVERTÊNCIA", "Selecione uma disciplina!");
			}
		}else{
			FacesUtil.setMessageAdvertencia("ADVERTÊNCIA", "Selecione uma série!");
		}
	}
	
	public void listarDisciplinasPorSerie(){
		if(serieSelecionada.equals("0")){
			listaDisciplinas = questaoDao.listarDisciplinasTodasAsSeries();
		}else{
			listaDisciplinas = questaoDao.listarDisciplinasPorSerie(Integer.parseInt(serieSelecionada));
		}
		if(listaDisciplinas == null || listaDisciplinas.size()<1){
			FacesUtil.setMessageAdvertencia("ADVERTÊNCIA", "Nenhuma disciplina cadastrada paraa série selecionada!");
		}
	}
	
	public void listarQuestoes(){
		questao.getSerie().setId(Integer.parseInt(serieSelecionada));
		questao.getDisciplina().setId(Integer.parseInt(disciplinaSelecionada));
		if(serieSelecionada.equals("0") && disciplinaSelecionada.equals("0")){
			questoes = questaoDao.listaTodasAsQuestoes();
		}else if(!serieSelecionada.equals("0") && disciplinaSelecionada.equals("0")){
			questoes = questaoDao.listaQuestoesTodasAsDisciplinaPorSerie(questao);
		}else if(serieSelecionada.equals("0") && !disciplinaSelecionada.equals("0")){
			questoes = questaoDao.listaQuestoesTodasAsSeriesPorDisciplina(questao);
		}else if(!serieSelecionada.equals("0") && !disciplinaSelecionada.equals("0")){
			questoes = questaoDao.listaQuestoesPorSerieEDisciplina(questao);
		}
		if(questoes != null && questoes.size()>0){
			setExibeDataTable(true);
		}else{
			FacesUtil.setMessageAdvertencia("ADVERTÊNCIA", "Nenhuma questão foi encontrada!");
		}
	}
	
	public void detalharQuestao(){
		RequestContext.getCurrentInstance().openDialog("modalDetalhaQuestao");
	}
}
