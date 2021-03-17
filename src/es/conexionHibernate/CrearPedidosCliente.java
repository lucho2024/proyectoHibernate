package es.conexionHibernate;

import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CrearPedidosCliente {

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
					
					//Crear pedidos del cliente
					Pedido pedido1 = new Pedido(new GregorianCalendar(2020,7,5),"efectivo");
					Pedido pedido2 = new Pedido(new GregorianCalendar(2020,6,15),"tarjeta");
					Pedido pedido3 = new Pedido(new GregorianCalendar(2020,8,2),"efectivo");
					
					//agregar pedidos creados al cleinte creado
					
					elCliente.agregarPedidos(pedido1);
					elCliente.agregarPedidos(pedido2);
					elCliente.agregarPedidos(pedido3);
					
					//guardar los pedidos en la vase de datos en la tabla pedido
					//gracias a las relaciones sabes que en cada pedido hay un cliente
					miSession.save(pedido1);
					miSession.save(pedido2);
					miSession.save(pedido3);
					
					miSession.getTransaction().commit();//guardar en la base de datos
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
