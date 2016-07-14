package controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.bean.ManagedBean;

import lombok.Getter;
import lombok.Setter;
import model.Serie;
import util.jsf.FacesUtil;
import dao.SerieDao;

@ManagedBean
public class SerieController {
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private SerieDao dao;
	private @Getter @Setter Serie serie;
	private @Getter @Setter List<Serie> series;
	private @Getter @Setter boolean exibeDataTable;
	
	public SerieController() {
		dao = new SerieDao();
		serie = new Serie();
		listarSeries();
	}
	public String teste(){
		return "logar";
	}
	public void cadastrarSerie(){
		try{
			dao.insert(serie);
			FacesUtil.setMessageSucesso("Sucesso", "Série Cadastrada!");
		}catch(Exception e){
			FacesUtil.setMessageSucesso("ERRO", e.getMessage());
		}
	}
	public void listarSeries(){
		series = dao.listarSeries();
		if(series.size()>0){
			setExibeDataTable(true);
		}else{
			FacesUtil.setMessageSucesso("Erro", "Nenhuma série encontrada");
			
		}
	}
	public void deletarSerie(){
		if(dao.remove(serie)){
			FacesUtil.setMessageSucesso("SUCESSO", "Registro deletado!");
		}else{
			FacesUtil.setMessageSucesso("ERRO", "Não foi possível deletar o registro!");
		}
		
	}
	
//	public void buscarUsuarios(){
//		usuario.setPerfil(Integer.parseInt(perfil));
//		if(usuario.getPerfil() == Integer.parseInt(Constantes.PERFIL_TODOS) && usuario.getLogin().equals("")){
//			usuarios = dao.listarTodosUsuarios(usuario);
//		}else if(!usuario.getLogin().equals("") && usuario.getPerfil() == Integer.parseInt(Constantes.PERFIL_TODOS)){
//			usuarios = dao.listarUsuarioPorLogin(usuario);
//		}else if(usuario.getPerfil() != Integer.parseInt(Constantes.PERFIL_TODOS) && usuario.getLogin().equals("")){
//			usuarios = dao.listarUsuariosPorPerfil(usuario);
//		}else if(usuario.getPerfil() != Integer.parseInt(Constantes.PERFIL_TODOS) && !usuario.getLogin().equals("")){
//			usuarios = dao.listarUsuariosPorPerfilELogin(usuario);
//		}
//		if(usuarios.size()>0){
//			setExibeDataTable(true);
//		}else{
//			FacesUtil.setMessage("Erro", "Nenhum usuário encontrado!");
//		}
//	}
	
	public static void Main(String [] args){
		
	}
}
