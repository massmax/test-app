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
@Table( name = "warehouse" )
public class Warehouse 
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private Integer id;
	
	@Column( name = "name", nullable = false )
	private String name;
	
	@Column( name = "capacity", nullable = true )
	private int capacity;
	
	@OneToMany( mappedBy = "warehouse", cascade = CascadeType.ALL )
	private List< Product > products = new ArrayList< Product >( );
	
	@OneToMany( mappedBy = "warehouse", cascade = CascadeType.ALL )
	private List< OrderItem > items = new ArrayList< OrderItem >( );

	public Integer getId( ) { return id; }

	public void setId( Integer id ) { this.id = id; }

	public String getName( ) { return name; }

	public void setName( String name ) { this.name = name; }

	public int getCapacity( ) { return capacity; }

	public void setCapacity( int capacity ) { this.capacity = capacity; }

	public List< Product > getProducts( ) { return products; }

	public void setProducts( List< Product > products ) { this.products = products; }
	
	public List< OrderItem > getItems( ) { return items; }

	public void setItems( List< OrderItem > items ) { this.items = items; }
	
	public static void connect ( Warehouse warehouse, Product product, OrderItem item )
	{
		warehouse.getProducts( ).add( product );
		product.setWarehouse( warehouse );
		
		warehouse.getItems( ).add( item );
		item.setWarehouse( warehouse );
	}

}
