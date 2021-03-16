package es.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InsertaCliente {

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
					Cliente cliente1 = new Cliente("Ramona","SOTO","vereda agua mona");//instanciar objeto cliente
					DetallesCliente detallesCliente =
							new DetallesCliente("lucho.dev","54654654","primer cliente");//instanciar objeto cliente
					
					//asociar los objetos
					cliente1.setDetallesCliente(detallesCliente);
					//esto guarda la informacion de las dos tablas relacionadas
					miSession.beginTransaction();//Empezar transacion;
					miSession.save(cliente1);//Guardar cliente
					miSession.getTransaction().commit();//guardar en la base de datos
					System.out.println("Registro existoso");
					
					miSession.close();//cerrar la session
					
				} finally {
					// TODO: handle finally clause
					miFactory.close();
				}

	}

}
