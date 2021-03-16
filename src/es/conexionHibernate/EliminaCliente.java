package es.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EliminaCliente {

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
					Cliente miCliente=miSession.get(Cliente.class, 3);
					if(miCliente!=null) {
						System.out.println("Voy a eliminar al cliente "+miCliente.getNombre());
						miSession.delete(miCliente);
					}
					miSession.getTransaction().commit();//guardar en la base de datos
					if(miCliente!=null)System.out.println("Eliminacion existosa");
					else System.out.println("Nada que eliminar");
					
					miSession.close();//cerrar la session
					
				} finally {
					// TODO: handle finally clause
					miFactory.close();
				}

	}

}
