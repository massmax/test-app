package ru.yma.v;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.yma.lec7.entities.Category;
import ru.yma.lec7.entities.Customer;
import ru.yma.lec7.entities.Order;
import ru.yma.lec7.entities.OrderItem;
import ru.yma.lec7.entities.Product;
import ru.yma.lec7.entities.Warehouse;
import ru.yma.lec7.services.ICustomerService;
import ru.yma.lec7.services.IProductService;
import ru.yma.lec7.services.IWarehouseService;

public class GenericBean 
{
	private static final Logger log = LoggerFactory.getLogger(GenericBean.class);
	
	private ICustomerService _customerService;
	private IProductService _productService;
	private IWarehouseService _warehouseService;
	
	public ICustomerService getCustomerService( ) { return _customerService; }
	public void setCustomerService( ICustomerService customerService ) { this._customerService = customerService; }
	
	public IProductService getProductService( ) { return _productService; }
	public void setProductService( IProductService productService ) { this._productService = productService; }
	
	public IWarehouseService getWarehouseService( ) { return _warehouseService; }
	public void setWarehouseService( IWarehouseService warehouseService ) { this._warehouseService = warehouseService; }
	
	public void init( )
	{
		try
		{
			Warehouse warehouse = new Warehouse( );
			warehouse.setName( "Open" );
			warehouse.setCapacity( 1000 );
			warehouse = _warehouseService.create( warehouse );
			
			
			Category category1 = new Category( );
			category1.setName( " Fruit " );
			category1 = _productService.createCategory( category1 );
			
			
			Category category2 = new Category( );
			category2.setName( " Vegetables  " );
			category2 = _productService.createCategory( category2 );
			
			Customer customer = new Customer( );
			customer.setName( " Customer_1 " );
			customer = _customerService.createCustomer( customer );
			
			
			Order order = new Order( );
			order.setCustomer( customer );
			order = _customerService.createOrder( order );		
			
			Order order2 = new Order( );
			order2.setCustomer( customer );
			order2 = _customerService.createOrder( order2 );
			
			Product product = new Product( );
			product.setName( " Orange " );
			product.setCategory( category1 );
			product.setWarehouse( warehouse );
			product.setPrice( 200.5f );
			product.setDescription( " Ecuador " );
			product.setDiscount( 2f );
			product.setQuantity( 100 );
			product = _productService.createProduct( product );
			
			
			OrderItem item = new OrderItem( );
			item.setOrder( order );
			item.setProduct( product );
			item.setWarehouse( warehouse );
			item.setQuantity( 10f );
			Order.connect( order, item );
			
			item = new OrderItem( );
			item.setOrder( order2 );
			item.setProduct( product );
			item.setWarehouse( warehouse );
			item.setQuantity( 20f );
			Order.connect( order2, item );
			
			
			product = new Product( );
			product.setName( " Tomatoes " );
			product.setCategory( category2 );
			product.setWarehouse( warehouse );
			product.setPrice( 150f );
			product.setDescription( " Russia " );
			product.setDiscount( 0f );
			product.setQuantity( 500f );
			product = _productService.createProduct( product );			

			
			item = new OrderItem( );
			item.setOrder( order );
			item.setProduct( product );
			item.setWarehouse( warehouse );
			item.setQuantity( 50f );
			Order.connect( order, item );
			
			_customerService.updateOrder( order );
			
			_productService.runOrder( order ); /********** order 1 ***********/
			

			
			
			product = new Product( );
			product.setName( " Cucumbers" );
			product.setCategory( category2 );
			product.setWarehouse( warehouse );
			product.setPrice( 1150f );
			product.setDescription( " England " );
			product.setDiscount( 2f );
			product.setQuantity( 1500f );
			product = _productService.createProduct( product );
			
			
			item = new OrderItem( );
			item.setOrder( order2 );
			item.setProduct( product );
			item.setWarehouse( warehouse );
			item.setQuantity( 500f );
			Order.connect( order2, item );
			
			_customerService.updateOrder( order2 );
			
			_productService.runOrder( order2 ); /********** order 2 ***********/
			
			//Warehouse.connect( warehouse, product1, item );
			//Warehouse.connect( warehouse, product2, item );
			//Category.connect( category1, product1 );
			//Category.connect( category2, product2 );
			//Product.connect( product1, item );
			//Product.connect( product2, item );
			//Order.connect( order1, item );
			//Customer.connect( customer1, order1 );

			
			
			log.info( order.toString( ) );
			log.info( order2.toString( ) );
			
		}
		catch ( Exception e ) 
		{
			log.error( e.getMessage( ) );
			e.printStackTrace();
		}
	}
		
	
}
