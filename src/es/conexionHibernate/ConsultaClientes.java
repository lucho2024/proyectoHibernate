package es.conexionHibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class ConsultaClientes {

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
			miSession.beginTransaction();//Empezar transacion;
			//consulta clientes
			List<Clientes> losClientes=miSession.createQuery("from Clientes").getResultList();
			//mostrar clientes
			recorreClientes(losClientes);
			//consulta :con parametro
			losClientes=miSession.createQuery("from Clientes cl where cl.apellidos='SOTO'").getResultList();
			recorreClientes(losClientes);
			//consulta con and y or
			losClientes=miSession.createQuery("from Clientes cl where cl.apellidos='rodriguez'"+
			"or cl.direccion='buga'").getResultList();
			recorreClientes(losClientes);
			//COMMIT
			miSession.getTransaction().commit();//guardar en la bae de datos
System.out.println("Terminado");
			
			
			miSession.close();//cerrar la session
			
		} finally {
			// TODO: handle finally clause
			miFactory.close();
		}
	}

	private static void recorreClientes(List<Clientes> losClientes) {
		for(Clientes unCliente:losClientes) {
			System.out.println(unCliente);
		}
	}

}
