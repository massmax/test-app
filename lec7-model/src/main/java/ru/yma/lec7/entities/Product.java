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
@Table( name = "product" )
public class Product
{	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private Integer id;
	
	@ManyToOne
	@JoinColumn( name = "id_category", nullable = false )
	private Category category;
	
	@ManyToOne
	@JoinColumn( name = "id_warehouse", nullable = false )
	private Warehouse warehouse;
	
	@OneToMany( mappedBy = "product", cascade = CascadeType.ALL )
	private List< OrderItem > items = new ArrayList< OrderItem >( );
	
	@Column( name = "name", nullable = false, length = 100 )
	private String name;
	
	@Column( name = "description", nullable = true )
	private String description;
	
	@Column( name = "price", nullable = true )
	private float price;
	
	@Column( name = "quantity", nullable = true )
	private float quantity;
	
	@Column( name = "discount", nullable = true )
	private float discount;

	public Integer getId( ) { return id; }

	public void setId( Integer id ) { this.id = id; }

	public Category getCategory( ) { return category; }

	public void setCategory( Category category ) { this.category = category; }

	public Warehouse getWarehouse( ) { return warehouse; }

	public void setWarehouse( Warehouse warehouse ) { this.warehouse = warehouse; }

	public String getName( ) { return name; }

	public void setName( String name ) { this.name = name; }

	public String getDescription( ) { return description; }

	public void setDescription( String description ) { this.description = description; }

	public float getPrice( ) { return price; }

	public void setPrice( float price ) { this.price = price; }

	public float getQuantity( ) { return quantity; }

	public void setQuantity( float quantity ) { this.quantity = quantity; }

	public float getDiscount( ) { return discount; }

	public void setDiscount( float discount ) { this.discount = discount; }

	public List< OrderItem > getItems( ) { return items; }

	public void setItems( List< OrderItem > items ) { this.items = items; }
	
	public static void connect( Product product, OrderItem item)
	{
		product.getItems( ).add( item );
		item.setProduct( product ); 
	}
}
