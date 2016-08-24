package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import model.Usuario;
import util.Constantes;
import util.Criptografia;
import util.jsf.FacesUtil;
import dao.UsuarioDao;

@ManagedBean
public class UsuarioController {
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private @Getter @Setter UsuarioDao dao;
	private @Getter @Setter Usuario usuario;
	private @Getter @Setter String perfil;
	private @Getter @Setter List<Usuario> usuarios;
	private @Getter @Setter boolean exibeMensagem;
	private @Getter @Setter boolean exibeDataTable = false;
	private @Getter @Setter String usuarioInvalido;
	
	
	public UsuarioController() {
		dao = new UsuarioDao();
		usuario = new Usuario();
		exibeMensagem = false;
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		this.setUsuarioInvalido(request.getParameter("usuarioInvalido"));
		if(usuarioInvalido != null && !usuarioInvalido.equals("") && usuarioInvalido.equals("true")){
			FacesUtil.setMessageError("ERRO", "Login ou senha inválidos!");
		}
	}
	public String teste(){
		return "logar";
	}
	public void setUsuario(){
		usuario = (Usuario) FacesUtil.getObjectSession("login");
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
			FacesUtil.setMessageError("ERRO", e.getMessage());
		}
	}
	
	public void buscarUsuarios(){
		usuario = new Usuario();
		usuario.setPerfil(Integer.parseInt(perfil));
		if(usuario.getPerfil() == Constantes.PERFIL_TODOS && usuario.getLogin().equals("")){
			usuarios = dao.listarTodosUsuarios(usuario);
		}else if(!usuario.getLogin().equals("") && usuario.getPerfil() == Constantes.PERFIL_TODOS){
			usuarios = dao.listarUsuarioPorLogin(usuario);
		}else if(usuario.getPerfil() != Constantes.PERFIL_TODOS && usuario.getLogin().equals("")){
			usuarios = dao.listarUsuariosPorPerfil(usuario);
		}else if(usuario.getPerfil() != Constantes.PERFIL_TODOS && !usuario.getLogin().equals("")){
			usuarios = dao.listarUsuariosPorPerfilELogin(usuario);
		}
		if(usuarios.size()>0){
			setExibeDataTable(true);
		}else{
			FacesUtil.setMessageError("Erro", "Nenhum usuário encontrado!");
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
