package dao;

import java.util.List;

import model.Serie;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SerieDao{
	Configuration config = new Configuration();

	public void insert(Serie serie) {
		config.configure();
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(serie);
		transaction.commit();
	}

	public List<Serie> listarSeries() {
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

	

}
