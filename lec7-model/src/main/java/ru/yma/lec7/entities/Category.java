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
@Table( name="category" )
public class Category
{	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private Integer id;
	
	@Column( name = "name", nullable = false, length = 100)
	private String name;
	
	@OneToMany( mappedBy = "category", cascade = CascadeType.ALL )
	private List< Product > products = new ArrayList< Product >( );

	public Integer getId( ) { return id; }

	public void setId( Integer id ) { this.id = id; }

	public String getName( ) { return name; }

	public void setName( String name ) { this.name = name; }

	public List<Product> getProducts( ) { return products; }

	public void setProducts( List< Product > products ) { this.products = products; }
	
	public static void connect( Category category, Product product)
	{
		category.getProducts( ).add( product );
		product.setCategory( category );
	}
}
