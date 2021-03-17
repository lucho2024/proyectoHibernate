package es.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ObtenerCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//crear el sessionfactory
		SessionFactory miFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetallesCliente.class)
				.buildSessionFactory();
		//crear session
		Session miSession=miFactory.openSession();

		try {
		
			miSession.beginTransaction();//Empezar transacion;
			
			//obtener Objeto DetallesCliente
			DetallesCliente detallesCliente=miSession.get(DetallesCliente.class, 1);
			
			System.out.println(detallesCliente);
			System.out.println(detallesCliente.getCliente());
			
			System.out.println("Ahora vamos a borrar en cascada");
			miSession.delete(detallesCliente);//Elimina
			miSession.getTransaction().commit();//guardar en la base de datos
			
		}catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			// TODO: handle finally clause
			miSession.close();//cerrar la session
			miFactory.close();
		}

	}

}
