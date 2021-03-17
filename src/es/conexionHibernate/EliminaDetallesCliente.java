package es.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EliminaDetallesCliente {

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
					DetallesCliente detalleDelCliente=miSession.get(DetallesCliente.class, 4);
					
					detalleDelCliente.getCliente().setDetallesCliente(null);//quitar la asociacion con detalle cliente
					
					if(detalleDelCliente!=null) {
						//System.out.println("Voy a eliminar al cliente "+detalleDelCliente.getNombre());
						miSession.delete(detalleDelCliente);
					}
					miSession.getTransaction().commit();//guardar en la base de datos
					
					if(detalleDelCliente!=null)System.out.println("Eliminacion existosa");
					else System.out.println("Nada que eliminar");
					
					miSession.close();//cerrar la session
					
				} finally {
					// TODO: handle finally clause
					miFactory.close();
				}

	}

}
