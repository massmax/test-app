package ru.yma.lec7.serviceModel;

import ru.yma.lec7.entities.Category;
import ru.yma.lec7.entities.Customer;
import ru.yma.lec7.entities.Order;
import ru.yma.lec7.entities.OrderItem;
import ru.yma.lec7.entities.Product;
import ru.yma.lec7.entities.Warehouse;
import ru.yma.lec7.serviceModel.object.SMCustomer;
import ru.yma.lec7.serviceModel.object.SMOrderItem;
import ru.yma.lec7.serviceModel.object.SMProduct;

public class ServiceModelToDb
{	
	public static Product createProductEntity( SMProduct smProduct, Category category, Warehouse warehouse )
	{
		Product product = new Product( );
		product.setName( smProduct.getName( ) );
		product.setDescription( smProduct.getDescription( ) );		
		product.setPrice( smProduct.getPrice( ) );
		product.setQuantity( smProduct.getQuantity( ) );
		product.setDiscount( smProduct.getDiscount( ) );
		product.setWarehouse( warehouse );
		product.setCategory( category );

		return product;
	}
	
	public static Product updateProductEntity( Product tempProduct, SMProduct smProduct )
	{
		tempProduct.setName( smProduct.getName( ) );
		tempProduct.setDescription( smProduct.getDescription( ) );		
		tempProduct.setPrice( smProduct.getPrice( ) );
		tempProduct.setQuantity( smProduct.getQuantity( ) );
		tempProduct.setDiscount( smProduct.getDiscount( ) );

		return tempProduct;
	}
	
	public static Customer createCustomerEntity( SMCustomer smCustomer)
	{
		Customer customer = new Customer( );
		customer.setName(smCustomer.getName( ) );

		return customer;
	}
	
	public static Customer updateCategoryEntity( Customer tempCustomer, SMCustomer smCustomer )
	{
		tempCustomer.setName( smCustomer.getName( ) );

		return tempCustomer;
	}
	
	public static OrderItem createOrderItemEntity( SMOrderItem smOrderItem, Order order, Product product, Warehouse warehouse )
	{
		OrderItem orderItem = new OrderItem( );
		orderItem.setOrder( order );
		orderItem.setProduct( product );
		orderItem.setWarehouse( warehouse );
		orderItem.setQuantity( smOrderItem.getQuantity( ) );

		return orderItem;
	}
	
	public static OrderItem updateOrderItemEntity( OrderItem orderItem, Product product, SMOrderItem smOrderItem )
	{
		orderItem.setProduct( product );
		orderItem.setQuantity( smOrderItem.getQuantity( ) );

		return orderItem;
	}
}
