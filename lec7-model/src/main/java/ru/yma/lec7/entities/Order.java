package ru.yma.lec7.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table( name = "orders" )
public class Order 
{	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private Integer id;
	
	@ManyToOne
	@JoinColumn( name = "id_customer", nullable = false )
	private Customer customer;
	
	@OneToMany( mappedBy = "order", cascade = CascadeType.ALL )
	private List< OrderItem > items = new ArrayList< OrderItem >( );

	public Integer getId( ) { return id; }

	public void setId( Integer id ) { this.id = id; }

	public Customer getCustomer( ) { return customer; }

	public void setCustomer( Customer customer ) { this.customer = customer; }

	public List< OrderItem > getItems( ) { return items; }

	public void setItems( List< OrderItem > items ) { this.items = items; }
	
	public static void connect( Order order, OrderItem item)
	{
		order.getItems( ).add( item );
		item.setOrder( order ); 
	}
	
	@Override
	public String toString( )
	{
		StringBuilder bld = new StringBuilder( );
		
		bld.append( String.format( "\"%s\" = { \n", customer.getName () ) );
		
		for ( OrderItem item : items )
		{
		bld.append( String.format( "\t\t %s = %f = %s, \n", item.getProduct( ).getName( ), item.getQuantity( ), item.getWarehouse( ).getName( ) ) );
		}
		
		bld.append( " } " );
		
		return bld.toString( );
	}
}
