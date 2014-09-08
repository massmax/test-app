package ru.yma.lec7.serviceModel;


import ru.yma.lec7.entities.Customer;
import ru.yma.lec7.entities.OrderItem;
import ru.yma.lec7.entities.Product;
import ru.yma.lec7.serviceModel.object.SMCustomer;
import ru.yma.lec7.serviceModel.object.SMOrderItem;
import ru.yma.lec7.serviceModel.object.SMProduct;

public class DbToServiceModel
{
	
	public static SMProduct getSMProduct( Product product )
	{
		SMProduct smProduct = new SMProduct( );
		smProduct.setName( product.getName( ) );
		smProduct.setDescription( product.getDescription( ) );		
		smProduct.setPrice( product.getPrice( ) );
		smProduct.setQuantity( product.getQuantity( ) );
		smProduct.setDiscount( product.getDiscount( ) );
		smProduct.setCategory( product.getCategory( ).getName( ) );
		
		return smProduct;
	}
	
	public static SMCustomer getSMCustomer( Customer customer )
	{
		SMCustomer smCustomer = new SMCustomer( );
		smCustomer.setName( customer.getName( ) );
		
		return smCustomer;
	}
	
	public static SMOrderItem getSMOrderItem( OrderItem item )
	{
		SMOrderItem smOrderItem = new SMOrderItem( );
		smOrderItem.setIdItem( item.getId( ) );
		smOrderItem.setIdOrder( item.getOrder( ).getId( ) );
		smOrderItem.setNameProduct( item.getProduct( ).getName( ) );
		smOrderItem.setNameWarehouse( item.getWarehouse( ).getName( ) );
		smOrderItem.setQuantity( item.getQuantity( ) );
		
		return smOrderItem;
	}
	
}
