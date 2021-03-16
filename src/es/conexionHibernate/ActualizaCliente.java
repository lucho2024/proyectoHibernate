package es.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ActualizaCliente {
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
			int ClienteId=1;
			miSession.beginTransaction();//Empezar transacion;
			//Clientes miCliente=miSession.get(Clientes.class, ClienteId);
			//miCliente.setNombre("luis hernando");
			miSession.createQuery("update Clientes set apellidos='montoya' where apellidos like 'S%'")
			.executeUpdate();
			miSession.createQuery("delete Clientes where id=4")
			.executeUpdate();
			miSession.getTransaction().commit();//guardar en la base de datos
			System.out.println("Registro actualizado");
			
			
		
			
			
			miSession.close();//cerrar la session
			
		} finally {
			// TODO: handle finally clause
			miFactory.close();
		}

	}
}
