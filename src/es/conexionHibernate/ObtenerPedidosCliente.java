package es.conexionHibernate;

import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ObtenerPedidosCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//crear el sessionfactory
				SessionFactory miFactory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Cliente.class)
						.addAnnotatedClass(DetallesCliente.class)
						.addAnnotatedClass(Pedido.class)
						.buildSessionFactory();
				//crear session
				Session miSession=miFactory.openSession();

				try {
					
					miSession.beginTransaction();//Empezar transacion;
					//obtener el cliente de la tabla clientes
					Cliente elCliente = miSession.get(Cliente.class, 6);					
					miSession.getTransaction().commit();//guardar en la base de datos
					System.out.println("Cliente: "+elCliente);
					System.out.println("Pedidos : "+elCliente.getPedidos());
					System.out.println("Registros existoso");
					
					
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					// TODO: handle finally clause
					miFactory.close();
					miSession.close();//cerrar la session
				}

	}

}
