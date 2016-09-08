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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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
	private @Getter @Setter Usuario usuarioSelecionado;
	
	
	public UsuarioController() {
		dao = new UsuarioDao();
		usuario = new Usuario();
		usuarioSelecionado = new Usuario();
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
	public void cadastrarUsuario(){
		try{
			usuario.setPerfil(Integer.parseInt(perfil));
			usuario.setSenha(Criptografia.encrypt(Constantes.NOVA_SENHA));
			usuario.setDtInclusao(df.format(new Date()));
			usuario.setDescPerfil(obtemDescPerfil());
			dao.insert(usuario);
			setExibeMensagem(true);
			FacesUtil.setMessageSucesso("Sucesso", "Usuário Cadastrado!");
		}catch(Exception e){
			FacesUtil.setMessageError("ERRO", e.getMessage());
		}
	}
	
	public void buscarUsuarios(){
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
			obtemDescPerfil();
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
	public String obtemDescPerfil(){
		String descPerfil = "";
		switch (Integer.parseInt(perfil)) {
		case Constantes.PERFIL_ADMINISTRADOR: descPerfil = "Administrador";
			break;
		case Constantes.PERFIL_ALUNO: descPerfil = "Aluno";
			break;
		case Constantes.PERFIL_PROFESSOR: descPerfil = "Professor";;
			break;
		default:
			descPerfil = "Perfil Inexistente";
			break;
		}
		return descPerfil;
	}
	
	public void deletarUsuario(){
		if(dao.remove(usuarioSelecionado)){
			FacesUtil.setMessageSucesso("SUCESSO", "Usuário removido com sucesso!");
		}else{
			FacesUtil.setMessageError("ERRO", "Não foi possível remover o usuário!");
		}
	}
	public void setaUsuarioSelecionado(){
		setUsuarioSelecionado(usuarioSelecionado);
		org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('modalConfirma').show()");

		
	}
	public void geraPDF() throws JRException{
		JasperReport path = JasperCompileManager.compileReport("relatorios\report2.jrxml");
		JasperPrint printReport = JasperFillManager.fillReport(path, null);
		JasperExportManager.exportReportToPdfFile(printReport, "relatorios\report2.jrxml");
	}
	public static void Main(String [] args) throws JRException{
		JasperReport path = JasperCompileManager.compileReport("relatorios\report2.jrxml");
		JasperPrint printReport = JasperFillManager.fillReport(path, null);
		JasperExportManager.exportReportToPdfFile(printReport, "relatorios\report2.jrxml");
	}
}
