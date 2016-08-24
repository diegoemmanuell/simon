package dao;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import model.UsuarioSeguranca;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class UsuarioDao{
	Configuration config = new Configuration();

	public void insert(Usuario usuario) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(usuario);
		transaction.commit();
	}

	public List<Usuario> listarTodosUsuarios(Usuario usuario) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		return session.createCriteria(Usuario.class).list();
	}

	public List<Usuario> listarUsuarioPorLogin(Usuario usuario) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		return session.createCriteria(Usuario.class).add(Restrictions.eq("login", usuario.getLogin())).list();
		
		
		
	}
	
	public List<UsuarioSeguranca> listarUsuarioPorLogin(UsuarioSeguranca usuario) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		List<Usuario> lista = session.createCriteria(Usuario.class).add(Restrictions.eq("login", usuario.getLogin())).list();
		List<UsuarioSeguranca> usuarios = new ArrayList<>();
		for(int i = 0; i < lista.size(); i++){
			UsuarioSeguranca u = new UsuarioSeguranca();
			u.setCpf(lista.get(i).getCpf());
			u.setDtInclusao(lista.get(i).getDtInclusao());
			u.setEmail(lista.get(i).getEmail());
			u.setId(lista.get(i).getId());
			u.setLogin(lista.get(i).getLogin());
			u.setMatricula(lista.get(i).getMatricula());
			u.setNome(lista.get(i).getNome());
			u.setPerfil(lista.get(i).getPerfil());
			u.setSenha(lista.get(i).getSenha());
			u.setStatusMatricula(lista.get(i).isStatusMatricula());
			u.setTurma(lista.get(i).getTurma());
			usuarios.add(u);
		}
		return usuarios;
		
		
		
	}

	public List<Usuario> listarUsuariosPorPerfil(Usuario usuario) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		return session.createCriteria(Usuario.class).add(Restrictions.eq("perfil", usuario.getPerfil())).list();
	}

	public List<Usuario> listarUsuariosPorPerfilELogin(Usuario usuario) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Criteria cr = session.createCriteria(Usuario.class);
		cr.add(Restrictions.eq("login", usuario.getLogin()));
		cr.add(Restrictions.eq("perfil", usuario.getPerfil()));
		return cr.list();
	}

	

}
