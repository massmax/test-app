package ru.yma.lec7.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name="customer" )
public class Customer
{	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private Integer id;
	
	@Column( name = "name", nullable = false, length = 100 )
	private String name;
	
	@OneToMany( mappedBy = "customer", cascade = CascadeType.ALL )
	private List< Order > orders = new ArrayList< Order >( );

	public Integer getId( ) { return id; }

	public void setId( Integer id ) { this.id = id; }

	public String getName( ) { return name; }

	public void setName( String name ) { this.name = name; }

	public List< Order > getOrders( ) { return orders; }

	public void setOrders( List< Order > orders ) { this.orders = orders; }
	
	public static void connect( Customer customer, Order order )
	{
		customer.getOrders( ).add( order );
		order.setCustomer( customer );
	}
}
