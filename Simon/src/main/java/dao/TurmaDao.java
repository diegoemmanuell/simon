package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Serie;
import model.Turma;
import model.Usuario;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import util.ConsultaGenerica;
import util.PathConsultasEnum;


public class TurmaDao{
	Configuration config = new Configuration();
	private ConsultaGenerica query = new ConsultaGenerica();
	public boolean insert(Turma turma) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			session.save(turma);
			transaction.commit();
			return true;
		}catch(HibernateException e){
			if(transaction !=null){
				transaction.rollback();
				e.printStackTrace();
			}
			return false;
		}
	}

	public List<Usuario> listarTodosUsuarios(Usuario usuario) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		return session.createCriteria(Usuario.class).list();
	}

	public List<Serie> listarSerie() {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		return session.createCriteria(Serie.class).list();
	}
	

	public boolean remove(Serie serie) {
		try{
			config.configure();
			SessionFactory factory = config.buildSessionFactory();
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(serie);
			tx.commit();
			return true;
		}catch(Exception e){
			return false;
		}
				
	}

	public List<SelectItem> listarSeries() {
			List<SelectItem> lista = new ArrayList<SelectItem>();
			config.configure();
			SessionFactory factory = config.buildSessionFactory();
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			try{
			Criteria cr = session.createCriteria(Serie.class);
			List list = cr.list();
			for(Iterator iterator = list.iterator(); iterator.hasNext();){
				Serie serie = (Serie) iterator.next();
				lista.add(new SelectItem(serie.getId(), serie.getNome()));
			}
			tx.commit();
			return lista;
			
		}catch(HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
			return null;
		}
		
		
	}

	@SuppressWarnings("unchecked")
	public List<Turma> listaTodasAsTurmas() {
		try{
			
			config.configure();
			SessionFactory factory = config.buildSessionFactory();
			Session session = factory.openSession();
			String script = "from Turma t";
			Query queryy = session.createQuery(script);
			return queryy.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Turma> listaTurmaPorSerie(Turma turma) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		String script = "from Turma t where t.serie.id = :serie";
		Query queryy = session.createQuery(script);
		queryy.setParameter("serie", turma.getSerie().getId());
		return queryy.list();
	}

	

}
