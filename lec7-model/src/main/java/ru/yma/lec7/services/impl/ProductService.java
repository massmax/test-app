package ru.yma.lec7.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.yma.lec7.entities.Category;
import ru.yma.lec7.entities.Order;
import ru.yma.lec7.entities.OrderItem;
import ru.yma.lec7.entities.Product;
import ru.yma.lec7.repositories.CategoryRepository;
import ru.yma.lec7.repositories.ProductRepository;
import ru.yma.lec7.services.IProductService;

public class ProductService implements IProductService
{
	private static final Logger log = LoggerFactory.getLogger( Product.class );
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/*
	 * Work with products
	 */

	@Transactional
	public Product createProduct( Product product )
	{
		List < Product > listProducts = productRepository.findByNameProduct( product.getName( ) );
		
		if ( ( listProducts != null ) && ( !listProducts.isEmpty( ) ) )
		{
			Product prod = listProducts.get( 0 );
			log.info("Zero item in List (name =" + prod.getName( ) + ", id=" + prod.getId( ) + ")" );
			
			if  ( prod.getName( ).equalsIgnoreCase(product.getName( ) ) )
			{
				if ( prod.getDescription( ).equalsIgnoreCase( product.getDescription( ) ) )
				{
					if ( prod.getCategory( ).getName( ).equalsIgnoreCase( product.getCategory( ).getName( ) ) )
					{
						prod.setDiscount( product.getDiscount( ) );
						prod.setPrice(  product.getPrice( ) );
						prod.setQuantity( prod.getQuantity( ) + product.getQuantity( ) );
						log.info( " This product has -> update" );
						return productRepository.save( prod );
					}
				}
				
			}
		}			
		log.info( "Product " + product.getName( ) + " created " );
		return productRepository.save( product );		
	}

	@Transactional( rollbackFor = Exception.class )
	public Product deleteProduct( Integer id )
			throws Exception
	{
		Product deletedProduct = productRepository.findOne( id );
		if ( deletedProduct == null )
		{
			throw new Exception( " Product not found " );
		}
		productRepository.delete( deletedProduct );
		log.info( "Product " + deletedProduct.getName( ) + " deleted " );
		return deletedProduct;
	}

	@Transactional
	public List< Product > getAllProduct( )
	{
		return productRepository.findAll( );
	}

	@Transactional( rollbackFor = Exception.class )
	public Product updateProduct( Product product )
			throws Exception
	{
		Product updatedProduct = productRepository.findOne( product.getId( ) );
		if ( updatedProduct == null )
		{
			throw new Exception( " Product not found " );
		}
		updatedProduct.setName( product.getName( ) );
		updatedProduct.setDescription( product.getDescription( ) );
		updatedProduct.setPrice( product.getPrice( ) );
		updatedProduct.setQuantity( product.getQuantity( ) );
		updatedProduct.setDiscount( product.getDiscount( ) );
		updatedProduct.setItems(product.getItems( ) );
		productRepository.save( updatedProduct );
		log.info( "Product " + updatedProduct.getName( ) + " updated " );
		return updatedProduct;
	}

	@Transactional
	public Product findByIdProduct( Integer id )

	{
		return productRepository.findOne( id );
	}
	
	@Transactional
	public Product findByNameProduct( String name )
	{
		List< Product > list = productRepository.findByNameProduct( name );
		return list.get( 0 );
	}

	
	/*
	 * Work with categories
	 */
	@Transactional
	public Category createCategory( Category category )
	{
		List< Category > listCategories = categoryRepository.findByNameCategory(category.getName( ) );
		if(  ! listCategories.isEmpty( ) )
		{
			log.info( " This category has" );
			return listCategories.get( 0 );
		}
		log.info( "Category " + category.getName( ) + " created " );
		return categoryRepository.save( category );
	}
	
	@Transactional( rollbackFor = Exception.class )
	public Category updateCategory( Category category )
			throws Exception
	{
		Category updatedCategory = categoryRepository.findOne( category.getId( ) );
		if ( updatedCategory == null )
		{
			throw new Exception( " Category not found " );
		}
		updatedCategory.setName( category.getName( ) );
		updatedCategory.setProducts( category.getProducts( ) );
		categoryRepository.save( updatedCategory );
		log.info( "Category " + updatedCategory.getName( ) + " updated " );
		return updatedCategory;
	}

	@Transactional( rollbackFor = Exception.class )
	public Category deleteCategory( Integer id ) throws Exception {
		Category deletedCategory = categoryRepository.findOne( id );
		if ( deletedCategory == null )
		{
			throw new Exception( " Product not found " );
		}
		categoryRepository.delete( deletedCategory );
		log.info( "Category " + deletedCategory.getName( ) + " deleted " );
		return deletedCategory;
	}
	
	@Transactional
	public Category findByNameCategory( String name )
	{
		List< Category > list = categoryRepository.findByNameCategory( name );
		return list.get( 0 );
	}

	@Transactional
	public void runOrder ( Order order )
	{
		List < OrderItem > list = order.getItems( );
		
		for ( OrderItem item : list )
		{
			Product product = item.getProduct( );
			if ( item.getQuantity( ) < product.getQuantity( ) )
			{
				try
				{
					product.setQuantity( product.getQuantity( ) - item.getQuantity( ) );
					productRepository.save( product );
					log.info( product.getName( ) + " product sold (method update) ");
				}
				catch (Exception e)
				{
					log.error( e.getMessage( ) );
				}
			}
			else if ( item.getQuantity( ) == product.getQuantity( ) )
				{
					try
					{
						productRepository.delete( product.getId( ) );
						log.info( product.getName( ) + " product sold (method deleted) ");
					}
					catch (Exception e)
					{
						log.error( e.getMessage( ) );
					}
				}
			else log.info( "Enough product " + product.getName( ) + " in warehouse" );
		}
	}
}
