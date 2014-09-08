package ru.yma.lec7.services;

import java.util.List;

import ru.yma.lec7.entities.Customer;
import ru.yma.lec7.entities.Order;
//import ru.yma.lec7.entities.OrderItem;
import ru.yma.lec7.entities.OrderItem;

public interface ICustomerService
{
	public Customer createCustomer( Customer customer );
	public Customer deleteCustomer( Integer id ) throws Exception;
	public List< Customer > getAllCustomers( );
	public Customer updateCustomer( Customer customer ) throws Exception;
	public Customer findByIdCustomer( Integer id );
	public Customer findByNameCustomer( String name );
	
	public Order createOrder( Order order );
	public Order deleteOrder( Integer id ) throws Exception;
	public List< Order > getAllOrders( );
	public Order updateOrder( Order order ) throws Exception;
	public Order findByIdOrder( Integer id );
	
	public OrderItem createOrderItem( OrderItem item );
	public OrderItem updateOrderItem( OrderItem item ) throws Exception;
	public OrderItem deleteOrderItem( Integer id ) throws Exception;
	public OrderItem findByIdOrderItem( Integer id );
}
