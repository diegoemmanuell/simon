package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import model.Usuario;
import util.Constantes;
import util.Criptografia;
import util.jsf.FacesUtil;
import dao.UsuarioDao;

@ManagedBean
public class UsuarioController {
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private UsuarioDao dao;
	private Usuario usuario;
	private String perfil;
	private List<Usuario> usuarios;
	private boolean exibeMensagem;
	private boolean exibeDataTable = false; 
	
	
	
	public boolean isExibeDataTable() {
		return exibeDataTable;
	}
	public void setExibeDataTable(boolean exibeDataTable) {
		this.exibeDataTable = exibeDataTable;
	}
	public boolean isExibeMensagem() {
		return exibeMensagem;
	}
	public void setExibeMensagem(boolean exibeMensagem) {
		this.exibeMensagem = exibeMensagem;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public UsuarioController() {
		dao = new UsuarioDao();
		usuario = new Usuario();
		exibeMensagem = false;
	}
	public String teste(){
		return "logar";
	}
	public void cadastrarUsuario(){
		try{
			usuario.setPerfil(Integer.parseInt(perfil));
			usuario.setSenha(Criptografia.encrypt(Constantes.NOVA_SENHA));
			usuario.setDtInclusao(df.format(new Date()));
			dao.insert(usuario);
			setExibeMensagem(true);
			FacesUtil.setMessageSucesso("Sucesso", "Usuário Cadastrado!");
		}catch(Exception e){
			FacesUtil.setMessageSucesso("ERRO", e.getMessage());
		}
	}
	
	public void buscarUsuarios(){
		usuario.setPerfil(Integer.parseInt(perfil));
		if(usuario.getPerfil() == Integer.parseInt(Constantes.PERFIL_TODOS) && usuario.getLogin().equals("")){
			usuarios = dao.listarTodosUsuarios(usuario);
		}else if(!usuario.getLogin().equals("") && usuario.getPerfil() == Integer.parseInt(Constantes.PERFIL_TODOS)){
			usuarios = dao.listarUsuarioPorLogin(usuario);
		}else if(usuario.getPerfil() != Integer.parseInt(Constantes.PERFIL_TODOS) && usuario.getLogin().equals("")){
			usuarios = dao.listarUsuariosPorPerfil(usuario);
		}else if(usuario.getPerfil() != Integer.parseInt(Constantes.PERFIL_TODOS) && !usuario.getLogin().equals("")){
			usuarios = dao.listarUsuariosPorPerfilELogin(usuario);
		}
		if(usuarios.size()>0){
			setExibeDataTable(true);
		}else{
			FacesUtil.setMessageSucesso("Erro", "Nenhum usuário encontrado!");
		}
	}
	public void limpar(){
		usuario.setCpf("");
		usuario.setEmail("");
		usuario.setLogin("");
		usuario.setNome("");
		usuario.setMatricula("");
		setPerfil("");
	}
	public static void Main(String [] args){
		
	}
}
