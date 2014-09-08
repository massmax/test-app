package ru.yma.lec7.services;

import java.util.List;

import ru.yma.lec7.entities.Category;
import ru.yma.lec7.entities.Order;
import ru.yma.lec7.entities.Product;

public interface IProductService
{
	public Product createProduct( Product product );
	public Product deleteProduct( Integer id ) throws Exception;
	public List< Product > getAllProduct( );
	public Product updateProduct( Product product ) throws Exception;
	public Product findByIdProduct( Integer id );
	public Product findByNameProduct( String name );
	
	public Category createCategory( Category category );
	public Category deleteCategory( Integer id ) throws Exception;
	public Category updateCategory( Category category ) throws Exception;
	public Category findByNameCategory( String name );

	public void runOrder( Order order );
}
