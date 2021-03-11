package es.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GuardaCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//crear el sessionfactory
		SessionFactory miFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Clientes.class)
				.buildSessionFactory();
		//crear session
		Session miSession=miFactory.openSession();
		
		try {
			Clientes cliente1 = new Clientes("luis","montoya","buga");//instanciar objeto cliente
			miSession.beginTransaction();//Empezar transacion;
			miSession.save(cliente1);//Guardar cliente
			miSession.getTransaction().commit();//guardar en la bae de datos
			System.out.println("Registro existoso");
			
			miSession.close();//cerrar la session
			
		} finally {
			// TODO: handle finally clause
			miFactory.close();
		}

	}

}
