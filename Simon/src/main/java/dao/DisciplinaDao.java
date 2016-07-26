package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Disciplina;
import model.Serie;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import util.ConsultaGenerica;


public class DisciplinaDao{
	Configuration config = new Configuration();
	private ConsultaGenerica query = new ConsultaGenerica();
	
	public boolean insert(Disciplina disciplina) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			session.save(disciplina);
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

	public List<Disciplina> listarDisciplinas() {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		return session.createCriteria(Disciplina.class).list();
	}
	

	public boolean remove(Disciplina disciplina) {
		try{
			config.configure();
			SessionFactory factory = config.buildSessionFactory();
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(disciplina);
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
	public List<Disciplina> listaTodasAsDisciplinas() {
		try{
			
			config.configure();
			SessionFactory factory = config.buildSessionFactory();
			Session session = factory.openSession();
			String script = "from Disciplina t";
			Query queryy = session.createQuery(script);
			return queryy.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Disciplina> listaDisciplinaPorSerie(Disciplina disciplina) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		String script = "from Disciplina d where d.serie.id = :serie";
		Query queryy = session.createQuery(script);
		queryy.setParameter("serie", disciplina.getSerie().getId());
		return queryy.list();
	}

	

}
