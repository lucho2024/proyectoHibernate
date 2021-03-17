package es.conexionHibernate;

import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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
					//Cliente elCliente = miSession.get(Cliente.class, 6);		
					Query<Cliente> consulta = miSession
							.createQuery("select cl from Cliente cl JOIN FETCH cl.pedidos where cl.id=:clienteId");
					
					consulta.setParameter("clienteId", 5);
					
					Cliente elCliente = consulta.getSingleResult();//carga en memoria toda la informacion del cliente
					miSession.getTransaction().commit();//guardar en la base de datos
				 	System.out.println("Cliente: "+elCliente);
					//System.out.println("Pedidos : "+elCliente.getPedidos());
					
					
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
