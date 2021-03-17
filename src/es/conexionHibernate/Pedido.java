package es.conexionHibernate;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity //convierte la clase en una entidad para hacer el mapeo
@Table(name="pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="fecha")
	private Date fecha;
	@Column(name="forma_pago")
	private String forma_pago;
	

	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})//muchos a uno
	@JoinColumn(name="clienteId")//join con la tabla cliente
	private Cliente cliente;
	
		
	public Pedido() {
		
	}

	public Pedido(Date fecha, String forma_pago) {
		super();
		this.fecha = fecha;
		this.forma_pago = forma_pago;
		
	}


	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public Date getFecha() {
		return fecha;
	}





	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}





	public String getForma_pago() {
		return forma_pago;
	}





	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}





	public Cliente getCliente() {
		return cliente;
	}





	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}





	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + ", forma_pago=" + forma_pago + ", cliente=" + cliente
				+ "]";
	}
	
	
	
}
