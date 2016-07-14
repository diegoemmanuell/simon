package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import model.Disciplina;
import model.Questao;
import model.Serie;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import util.ConsultaGenerica;


public class QuestaoDao{
	Configuration config = new Configuration();
	private ConsultaGenerica query = new ConsultaGenerica();
	
	public boolean insert(Questao questao) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			session.save(questao);
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
	public List<Questao> listaTodasAsQuestoes() {
		try{
			
			config.configure();
			SessionFactory factory = config.buildSessionFactory();
			Session session = factory.openSession();
			String script = "from Questao q";
			Query queryy = session.createQuery(script);
			return queryy.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Questao> listaQuestoesTodasAsDisciplinaPorSerie(Questao questao) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		String script = "from Questao d where d.serie.id = :serie";
		Query queryy = session.createQuery(script);
		queryy.setParameter("serie", questao.getSerie().getId());
		return queryy.list();
	}

	public List<SelectItem> listarDisciplinasPorSerie(int serieSelecionada) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		String script= "from Disciplina d where d.serie.id = :serie";
		Query q = session.createQuery(script);
		q.setParameter("serie", serieSelecionada);
		List list = q.list();
		for(Iterator iterator = list.iterator(); iterator.hasNext();){
			Disciplina disciplina = (Disciplina) iterator.next();
			lista.add(new SelectItem(disciplina.getId(), disciplina.getNome()));
		}
		return lista;
	}

	public List<SelectItem> listarDisciplinasTodasAsSeries() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		String script= "from Disciplina";
		Query q = session.createQuery(script);
		List list = q.list();
		for(Iterator iterator = list.iterator(); iterator.hasNext();){
			Disciplina disciplina = (Disciplina) iterator.next();
			lista.add(new SelectItem(disciplina.getId(), disciplina.getNome()));
		}
		return lista;
	}

	public List<Questao> listaQuestoesTodasAsSeriesPorDisciplina(Questao questao) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		String script = "from Questao q where q.disciplina.id = :disciplina";
		Query queryy = session.createQuery(script);
		queryy.setParameter("disciplina", questao.getDisciplina().getId());
		return queryy.list();
	}

	public List<Questao> listaQuestoesPorSerieEDisciplina(Questao questao) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		String script = "from Questao q where q.disciplina.id = :disciplina and q.serie.id = :serie";
		Query queryy = session.createQuery(script);
		queryy.setParameter("disciplina", questao.getDisciplina().getId());
		queryy.setParameter("serie", questao.getSerie().getId());
		return queryy.list();
	}

	

}
