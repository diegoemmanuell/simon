package dao;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

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
	
	public Usuario listarUsuarioPorLogin(String login) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		List<UsuarioSeguranca> usuarios = new ArrayList<>();
		String script = "select * from usuario u where u.login = '" + login + "';";
		List<Usuario> lista = session.createCriteria(Usuario.class).add(Restrictions.eq("login", login)).list();
		Usuario u = new Usuario();
		if(lista != null && lista.size()>0){
			for(int i = 0; i < lista.size(); i++){
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
			}
			
			return u;
		}else{
			return null;
		}
		
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

	public boolean remove(Usuario usuario) {
		try{
			if(usuario.getLogin() != null){
				config.configure();
				SessionFactory factory = config.buildSessionFactory();
				Session session = factory.openSession();
				Transaction tx = session.beginTransaction();
				session.delete(usuario);
				tx.commit();
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}

	

}
