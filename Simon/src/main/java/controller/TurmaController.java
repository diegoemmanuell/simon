package controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import lombok.Getter;
import lombok.Setter;
import model.Serie;
import model.Turma;
import util.jsf.FacesUtil;
import dao.TurmaDao;

@ManagedBean
public class TurmaController {
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private TurmaDao dao;
	private @Getter @Setter Turma turma;
	private @Getter @Setter List<Serie> series;
	private @Getter @Setter List<SelectItem> listaSeries;
	private @Getter @Setter int serie;
	private @Getter @Setter String serieSelecionada;
	private @Getter @Setter List<Turma> turmas;
	private @Getter @Setter boolean exibeDataTable;
	
	public TurmaController() {
		dao = new TurmaDao();
		turma = new Turma();
		listaSeries = dao.listarSeries();
		turma.setSerie(new Serie());
		exibeDataTable = false;
	}
	public void cadastrarTurma(){
		if(serieSelecionada != null && !serieSelecionada.equals("")){
			turma.getSerie().setId(Integer.parseInt(serieSelecionada));
			if(dao.insert(turma)){
				FacesUtil.setMessageSucesso("SUCESSO", "Turma adicionada!");
			}else{
				FacesUtil.setMessageSucesso("ERRO", "Não foi possível registrar a turma!");
			}
		}else{
			FacesUtil.setMessageSucesso("ADVERTÊNCIA", "Selecione uma série!");
		}
	}
	public void buscarTurmas(){
		serie = Integer.parseInt(serieSelecionada);
		turma.getSerie().setId(serie);
		if(serie == 0){
			turmas = dao.listaTodasAsTurmas();
		}else{
			turmas = dao.listaTurmaPorSerie(turma);
		}
		if(turmas != null && turmas.size()>0){
			setExibeDataTable(true);
		}else{
			FacesUtil.setMessageSucesso("ERRO", "Nenhuma turma foi encontrada!");
		}
	}
//	public void cadastrarSerie(){
//		try{
//			dao.insert(serie);
//			FacesUtil.setMessage("Sucesso", "Série Cadastrada!");
//		}catch(Exception e){
//			FacesUtil.setMessage("ERRO", e.getMessage());
//		}
//	}
//	public void listarSeries(){
//		series = dao.listarSeries();
//		if(series.size()>0){
//			setExibeDataTable(true);
//		}else{
//			FacesUtil.setMessage("Erro", "Nenhuma série encontrada");
//			
//		}
//	}
//	public void deletarSerie(){
//		if(dao.remove(serie)){
//			FacesUtil.setMessage("SUCESSO", "Registro deletado!");
//		}else{
//			FacesUtil.setMessage("ERRO", "Não foi possível deletar o registro!");
//		}
//		
//	}
	
}
