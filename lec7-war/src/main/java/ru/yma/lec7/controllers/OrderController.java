package ru.yma.lec7.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;






import ru.yma.lec7.entities.Order;
import ru.yma.lec7.entities.OrderItem;
import ru.yma.lec7.entities.Product;
import ru.yma.lec7.entities.Warehouse;
import ru.yma.lec7.serviceModel.DbToServiceModel;
import ru.yma.lec7.serviceModel.ServiceModelToDb;
import ru.yma.lec7.serviceModel.object.SMOrderItem;
import ru.yma.lec7.serviceModel.request.OrderItemRequest;
import ru.yma.lec7.serviceModel.request.OrderRequest;
import ru.yma.lec7.serviceModel.response.ErrorResponse;
import ru.yma.lec7.serviceModel.response.OrderResponse;
import ru.yma.lec7.serviceModel.response.Response;
import ru.yma.lec7.services.ICustomerService;
import ru.yma.lec7.services.IProductService;
import ru.yma.lec7.services.IWarehouseService;

@Controller
@RequestMapping( "/warehouses" )
public class OrderController
{	
	private static final Logger log = LoggerFactory.getLogger( OrderController.class );
	
	@Autowired
	@Qualifier( "productService" )
	private IProductService _productService;
	
	@Autowired
	@Qualifier( "warehouseService" )
	private IWarehouseService _warehouseService;
	
	@Autowired
	@Qualifier( "customerService" )
	private ICustomerService _customerService;
		
	
	@RequestMapping( value = "/{id_warehouse}/orders", method = RequestMethod.GET )
	public @ResponseBody Response getAllOrders( @PathVariable( "id_warehouse" ) Integer id_warehouse )
	{
		try
		{
			List< OrderItem > orderItemList = _warehouseService.findById( id_warehouse ).getItems( );
			List< SMOrderItem > smOrderItemList = new ArrayList< SMOrderItem >( );
			for ( OrderItem item : orderItemList )
			{
				SMOrderItem smOrderItem = DbToServiceModel.getSMOrderItem( item );
				smOrderItemList.add( smOrderItem );
				log.info( smOrderItem.toString( ));
				
			}
			log.info( smOrderItemList.toString( ) );
			OrderResponse response = new OrderResponse( );
			response.setOrderItemList( smOrderItemList );
			response.setStatus( "OK" );
			return response;
		}
		catch( Exception e )
		{
			log.error( e.getMessage( ) );
			e.printStackTrace( );
			ErrorResponse errorResponse = new ErrorResponse( );
			errorResponse.setStatus( "Fail" );
			errorResponse.setMessage( e.getMessage( ) );
			return errorResponse;
		}
	}
	
	@RequestMapping( value = "/{id_warehouse}/orders/{id_order}", method = RequestMethod.GET )
	public @ResponseBody Response getOrder( @PathVariable( "id_warehouse" ) Integer id_warehouse,
											@PathVariable( "id_order" ) Integer id_order )
	{
		try
		{
			Order order = _customerService.findByIdOrder( id_order );
			List< SMOrderItem > smOrderItemList = new ArrayList< SMOrderItem >( );
			for (OrderItem item : order.getItems( ) )
			{
				SMOrderItem smOrderItem = DbToServiceModel.getSMOrderItem( item );
				smOrderItemList.add( smOrderItem );
			}
			
			OrderResponse response = new OrderResponse( );
			response.setOrderItemList( smOrderItemList );			
			return response;
		}
		catch( Exception e )
		{
			ErrorResponse errorResponse = new ErrorResponse( );
			errorResponse.setStatus( "Fail" );
			errorResponse.setMessage( e.getMessage( ) );
			return errorResponse;
		}
	}
	
	
	@RequestMapping( value = "/{id_warehouse}/orders/{id_order}/items",
					 method = RequestMethod.POST,
					 produces = "application/json",
					 consumes = "application/json" )
	public @ResponseBody Response addOrderItem( @PathVariable( "id_warehouse" ) Integer id_warehouse,
												@PathVariable( "id_order" ) Integer id_order,
												@RequestBody OrderItemRequest request )
	{
		try
		{						
			SMOrderItem smOrderItem = request.getSmOrderItem( );
			Order order = _customerService.findByIdOrder( id_order );
			Warehouse warehouse = _warehouseService.findById( id_warehouse );
			Product product = _productService.findByNameProduct( smOrderItem.getNameProduct( ) );
			
			OrderItem orderItem = ServiceModelToDb.createOrderItemEntity( smOrderItem, order, product, warehouse );
			orderItem = _customerService.createOrderItem( orderItem );
			
			
			OrderResponse response = new OrderResponse( );
			response.setSMOrderItem( DbToServiceModel.getSMOrderItem( orderItem ) );
			response.setStatus( "ok, item order created");
			
			return response;
			
		}
		catch( Exception e )
		{
			ErrorResponse errorResponse = new ErrorResponse( );
			errorResponse.setStatus( "Failed to create" );
			errorResponse.setMessage( e.getMessage( ) );
			return errorResponse;
		}	
	}
	
	@RequestMapping( value = "/{id_warehouse}/orders",
			 method = RequestMethod.POST,
			 produces = "application/json",
			 consumes = "application/json" )
	public @ResponseBody Response addOrder( @PathVariable( "id_warehouse" ) Integer id_warehouse,
											@RequestBody OrderRequest request )
	{
		try
		{						
			List< SMOrderItem > smOrderList = new ArrayList< SMOrderItem >( );
			Order order = new Order( );
			order.setCustomer( _customerService.findByNameCustomer( request.getNameCustomer( ) ) );
			order = _customerService.createOrder( order );
			Warehouse warehouse = _warehouseService.findById( id_warehouse );
			for ( SMOrderItem item : request.getSmOrderList( ) )
			{				
				Product product = _productService.findByNameProduct( item.getNameProduct( ) );				
				OrderItem orderItem = ServiceModelToDb.createOrderItemEntity( item, order, product, warehouse );
				orderItem = _customerService.createOrderItem( orderItem );
				smOrderList.add( DbToServiceModel.getSMOrderItem( orderItem ) );				
			}
			
			OrderResponse response = new OrderResponse( );
			response.setOrderItemList( smOrderList );
			response.setStatus( "ok, order created");
			
			return response;
			
		}
		catch( Exception e )
		{
			ErrorResponse errorResponse = new ErrorResponse( );
			errorResponse.setStatus( "Failed to create" );
			errorResponse.setMessage( e.getMessage( ) );
			return errorResponse;
		}	
	}
	
	
	@RequestMapping( value = "/{id_warehouse}/orders/{id_order}",
					 method = RequestMethod.DELETE,
					 produces = "application/json",
					 consumes = "application/json" )
	public @ResponseBody Response deleteOrder( @PathVariable( "id_warehouse" ) Integer id_warehouse,
											   @PathVariable( "id_order" ) Integer id_order )
	{
		try
		{
			Order order = _customerService.deleteOrder( id_order );
			List< OrderItem > itemsList = order.getItems( );
			List< SMOrderItem > smOrderList = new ArrayList< SMOrderItem >( );
			for ( OrderItem item: itemsList)
			{
				SMOrderItem smOrderItem = DbToServiceModel.getSMOrderItem( item );
				smOrderList.add( smOrderItem );
			}
			
			OrderResponse response = new OrderResponse( );
			response.setOrderItemList( smOrderList );
			response.setStatus( "ok, order deleted");
			
			return response;
		}
		catch( Exception e )
		{
			ErrorResponse errorResponse = new ErrorResponse( );
			errorResponse.setStatus( "Failed to delete" );
			errorResponse.setMessage( e.getMessage( ) );			
			return errorResponse;
		}
	}
	
	@RequestMapping( value = "/{id_warehouse}/orders/{id_order}/items/{id_item}",
			 method = RequestMethod.DELETE,
			 produces = "application/json",
			 consumes = "application/json" )
	public @ResponseBody Response deleteOrderItem( @PathVariable( "id_warehouse" ) Integer id_warehouse,
											   	   @PathVariable( "id_order" ) Integer id_order,
											   	   @PathVariable( "id_item" ) Integer id_item)
	{
		try
		{
			OrderItem itemOrder = _customerService.findByIdOrderItem( id_item );
			Order order = _customerService.findByIdOrder( id_order );
			
			List< OrderItem > itemsList = order.getItems( );
			SMOrderItem smOrderItem = null;
			for ( OrderItem item : itemsList )
			{
				if ( item.getId( ) == itemOrder.getId( ) )
				{
					smOrderItem = DbToServiceModel.getSMOrderItem( itemOrder );
					itemOrder = _customerService.deleteOrderItem( id_item );
				}
			}			
						
			OrderResponse response = new OrderResponse( );
			response.setSMOrderItem( smOrderItem );
			response.setStatus( "ok, item order deleted");
			
			return response;
		}
		catch( Exception e )
		{
			ErrorResponse errorResponse = new ErrorResponse( );
			errorResponse.setStatus( "Failed to delete" );
			errorResponse.setMessage( e.getMessage( ) );			
			return errorResponse;
		}
	}
	
	
	@RequestMapping( value = "/{id_warehouse}/orders/{id_order}",
					 method = RequestMethod.PUT,
					 produces = "application/json",
					 consumes = "application/json" )
	public @ResponseBody Response updateOrder( @PathVariable( "id_warehouse" ) Integer id_warehouse,
											   @PathVariable( "id_order" ) Integer id_order,
											   @RequestBody OrderRequest request )
	{
		try
		{
			List< SMOrderItem > smOrderItemList = new ArrayList< SMOrderItem >( );
			List< OrderItem > itemList = _customerService.findByIdOrder( id_order ).getItems( );
			
			for ( OrderItem orderItem : itemList )
			{
				for ( SMOrderItem smOrderItem : request.getSmOrderList( ) )
				{
					if ( smOrderItem.getIdItem( ) == orderItem.getId( ) )
					{
						log.info( orderItem.getProduct( ).toString( ) );
						log.info( Float.toString( orderItem.getQuantity( ) ) );
						log.info( "-------------------------------------------" );
						Product product = _productService.findByNameProduct( smOrderItem.getNameProduct( ) );
						orderItem = ServiceModelToDb.updateOrderItemEntity( orderItem, product, smOrderItem);
						orderItem = _customerService.updateOrderItem( orderItem );
						log.info( orderItem.getProduct( ).toString( ) );
						log.info( Float.toString( orderItem.getQuantity( ) ) );
						smOrderItemList.add( DbToServiceModel.getSMOrderItem( orderItem ) );
					}
				}				
			}
			OrderResponse response = new OrderResponse( );
			response.setOrderItemList( smOrderItemList );
			response.setStatus( "ok, order updated");
			return response;
			
		}
		catch( Exception e )
		{
			ErrorResponse errorResponse = new ErrorResponse( );
			errorResponse.setStatus( "Failed to update" );
			errorResponse.setMessage( e.getMessage( ) );
			return errorResponse;	
		}		
	}
	
	@RequestMapping( value = "/{id_warehouse}/orders/{id_order}/items/{id_item}",
			 method = RequestMethod.PUT,
			 produces = "application/json",
			 consumes = "application/json" )
	public @ResponseBody Response updateOrderItem( @PathVariable( "id_warehouse" ) Integer id_warehouse,
												   @PathVariable( "id_order" ) Integer id_order,
												   @PathVariable( "id_item" ) Integer id_item,
												   @RequestBody OrderItemRequest request )
	{
		try
		{
			OrderItem orderItem = _customerService.findByIdOrderItem( id_item );
			SMOrderItem smOrderItem = request.getSmOrderItem( );
			if (orderItem.getId( ) == smOrderItem.getIdItem( ) )
			{
				Product product = _productService.findByNameProduct( smOrderItem.getNameProduct( ) );
				orderItem = ServiceModelToDb.updateOrderItemEntity(orderItem, product, smOrderItem);
				orderItem = _customerService.updateOrderItem( orderItem );
			}
			OrderResponse response = new OrderResponse( );
			response.setSMOrderItem( smOrderItem );
			response.setStatus( "ok, order item updated");
			return response;
			
		}
		catch( Exception e )
		{
			ErrorResponse errorResponse = new ErrorResponse( );
			errorResponse.setStatus( "Failed to update" );
			errorResponse.setMessage( e.getMessage( ) );
			return errorResponse;	
		}		
	}
}	
	