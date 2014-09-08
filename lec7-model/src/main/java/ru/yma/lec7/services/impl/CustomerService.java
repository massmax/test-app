package ru.yma.lec7.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.yma.lec7.entities.Customer;
import ru.yma.lec7.entities.Order;
import ru.yma.lec7.entities.OrderItem;
//import ru.yma.lec7.entities.OrderItem;
import ru.yma.lec7.repositories.CustomerRepository;
import ru.yma.lec7.repositories.OrderItemRepository;
//import ru.yma.lec7.repositories.OrderItemRepository;
import ru.yma.lec7.repositories.OrderRepository;
import ru.yma.lec7.services.ICustomerService;

public class CustomerService implements ICustomerService
{
	private static final Logger log = LoggerFactory.getLogger( CustomerService.class );
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	/*
	 * Work with customers
	 */
	@Transactional
	public Customer createCustomer( Customer customer )
	{
		List< Customer > listCustomers = customerRepository.findByNameCustomer(customer.getName( ) );
		if(  ! listCustomers.isEmpty( ) )
		{
			log.info( " This customer has" );
			return listCustomers.get( 0 );
		}
		log.info( "Customer " + customer.getName( ) + " created " );
		return customerRepository.save( customer );
	}

	@Transactional( rollbackFor = Exception.class )
	public Customer deleteCustomer( Integer id ) 
			throws Exception
	{
		Customer deletedCustomer = customerRepository.findOne( id );
		if ( deletedCustomer == null )
		{
			throw new Exception( " Customer not found ");
		}
		customerRepository.delete( deletedCustomer );
		log.info( "Customer " + deletedCustomer.getName( ) + " deleted " );
		return deletedCustomer;
	}
	
	@Transactional
	public List< Customer > getAllCustomers( )
	{
		return customerRepository.findAll( );
	}
	
	@Transactional( rollbackFor = Exception.class )
	public Customer updateCustomer(Customer customer)
			throws Exception
	{
		Customer updatedCustomer = customerRepository.findOne( customer.getId( ) );
		if ( updatedCustomer == null )
		{
			throw new Exception( " Customer not found " );
		}
		updatedCustomer.setName( customer.getName( ) );
		updatedCustomer.setOrders( customer.getOrders( ) );
		customerRepository.save( updatedCustomer );
		log.info( "Customer " + updatedCustomer.getName( ) + " updated " );
		return updatedCustomer;
	}
	
	@Transactional
	public Customer findByIdCustomer( Integer id )
	{
		return customerRepository.findOne( id );
	}
	
	@Transactional
	public Customer findByNameCustomer( String name )
	{
		List< Customer > list = customerRepository.findByNameCustomer( name ); 
		return list.get( 0 );
	}

	/*
	 * Work with order
	 */
	@Transactional
	public Order createOrder( Order order )
	{
		Order createOrder = order;		
		log.info( " Order created" );
		return orderRepository.save( createOrder );
	}
	
	@Transactional
	public Order deleteOrder( Integer id ) throws Exception
	{
		Order deletedOrder = orderRepository.findOne( id );
		if ( deletedOrder == null )
		{
			throw new Exception( " Order not found ");
		}
		orderRepository.delete( deletedOrder );
		log.info( " Order deleted" );
		return deletedOrder;
	}
	
	@Transactional
	public List< Order > getAllOrders( )
	{
		return orderRepository.findAll( );
	}
	
	@Transactional
	public Order updateOrder( Order order ) throws Exception
	{
		Order updatedOrder = orderRepository.findOne( order.getId( ) );
		if ( updatedOrder == null )
		{
			throw new Exception( " Order not found " );
		}
		updatedOrder.setItems( order.getItems( ) );
		orderRepository.save( updatedOrder );
		log.info( " Order updated" );
		return updatedOrder;
	}
	
	@Transactional
	public Order findByIdOrder( Integer id )
	{
		return orderRepository.findOne( id );
	}

	@Transactional
	public OrderItem deleteOrderItem( Integer id ) throws Exception
	{
		OrderItem deletedOrderItem = orderItemRepository.findOne( id );
		if ( deletedOrderItem == null )
		{
			throw new Exception( " Order item not found ");
		}
		orderItemRepository.delete( deletedOrderItem );
		log.info( " Order item deleted" );
		return deletedOrderItem;
	}
	
	@Transactional
	public OrderItem findByIdOrderItem( Integer id )
	{
		return orderItemRepository.findOne( id );
	}
	
	@Transactional
	public OrderItem updateOrderItem( OrderItem item ) throws Exception
	{
		OrderItem updatedOrderItem = orderItemRepository.findOne( item.getId( ) );
		if ( updatedOrderItem == null )
		{
			throw new Exception( " Order item not found " );
		}
		updatedOrderItem.setOrder( item.getOrder( ) );
		updatedOrderItem.setProduct( item.getProduct( ) );
		updatedOrderItem.setWarehouse( item.getWarehouse( ) );
		updatedOrderItem.setQuantity( item.getQuantity( ) );
		orderItemRepository.save( updatedOrderItem );
		log.info( " Order item updated" );
		return updatedOrderItem;
	}
	
	@Transactional
	public OrderItem createOrderItem( OrderItem item )
	{
		OrderItem createOrderItem = item;		
		log.info( " Order item created" );
		return orderItemRepository.save( createOrderItem );
	}
}
