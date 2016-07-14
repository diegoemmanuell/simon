package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.*;
import org.hibernate.criterion.Restrictions;

import model.Usuario;

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
