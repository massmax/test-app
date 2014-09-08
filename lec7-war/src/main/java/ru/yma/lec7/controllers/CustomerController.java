package ru.yma.lec7.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import ru.yma.lec7.entities.Customer;
import ru.yma.lec7.entities.Order;
import ru.yma.lec7.entities.OrderItem;
import ru.yma.lec7.serviceModel.DbToServiceModel;
import ru.yma.lec7.serviceModel.ServiceModelToDb;
import ru.yma.lec7.serviceModel.object.SMCustomer;
import ru.yma.lec7.serviceModel.object.SMOrderItem;
import ru.yma.lec7.serviceModel.request.CustomerRequest;
import ru.yma.lec7.serviceModel.response.CustomerResponse;
import ru.yma.lec7.serviceModel.response.ErrorResponse;
import ru.yma.lec7.serviceModel.response.OrderResponse;
import ru.yma.lec7.serviceModel.response.Response;
import ru.yma.lec7.services.ICustomerService;
import ru.yma.lec7.services.IProductService;
import ru.yma.lec7.services.IWarehouseService;

@Controller
@RequestMapping( "/warehouses" )
public class CustomerController
{	
	@Autowired
	@Qualifier( "productService" )
	private IProductService _productService;
	
	@Autowired
	@Qualifier( "warehouseService" )
	private IWarehouseService _warehouseService;
	
	@Autowired
	@Qualifier( "customerService" )
	private ICustomerService _customerService;
		
	@RequestMapping( value = "/{id_warehouse}/customers", method = RequestMethod.GET )
	public @ResponseBody Response getAllCustomers( @PathVariable( "id_warehouse" ) Integer id_warehouse )
	{
		try
		{
			List< Customer > customersList = _customerService.getAllCustomers( );			
			List< SMCustomer > smCustomersList = new ArrayList< SMCustomer >( );
			for ( Customer customer : customersList )
			{
				SMCustomer smCustomer = DbToServiceModel.getSMCustomer( customer ); 
				smCustomersList.add( smCustomer );
			}
			
			CustomerResponse response = new CustomerResponse( );
			response.setSMCustomersList( smCustomersList );
			
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
	
	@RequestMapping( value = "/{id_warehouse}/customers",
					 method = RequestMethod.POST,
					 produces = "application/json",
					 consumes = "application/json" )
	public @ResponseBody Response addCustomer( @RequestBody CustomerRequest request )
	{
		try
		{			
			SMCustomer smCustomer = request.getSMCustomer( );			
			Customer customer = ServiceModelToDb.createCustomerEntity( smCustomer );
			customer = _customerService.createCustomer( customer );			
			
			CustomerResponse response = new CustomerResponse( );
			response.setSMCustomer( DbToServiceModel.getSMCustomer( customer ) );
			response.setStatus( "ok, created");
			
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
	
	@RequestMapping( value = "/{id_warehouse}/customers/{id_customer}",
					 method = RequestMethod.DELETE,
					 produces = "application/json",
					 consumes = "application/json" )
	public @ResponseBody Response deleteCustomer( @PathVariable( "id_warehouse" ) Integer id_warehouse,
												  @PathVariable( "id_customer" ) Integer id_customer)
	{
		try
		{
			Customer customer = _customerService.findByIdCustomer( id_customer );
			SMCustomer smCustomer = DbToServiceModel.getSMCustomer( customer );
			CustomerResponse response = new CustomerResponse( );
			response.setStatus( "deleted " );
			response.setSMCustomer( smCustomer );
			customer = _customerService.deleteCustomer( id_customer );			
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
	
	@RequestMapping( value = "/{id_warehouse}/customers/{id_customer}",
					 method = RequestMethod.PUT,
					 produces = "application/json",
					 consumes = "application/json" )
	public @ResponseBody Response editProductInWarehouse( @PathVariable( "id_warehouse" ) Integer id_warehouse,
														  @PathVariable( "id_customer" ) Integer id_customer,
														  @RequestBody CustomerRequest request )
	{
		try
		{
			Customer customer = _customerService.findByIdCustomer( id_customer );
			SMCustomer smCustomer = request.getSMCustomer( );
			customer = ServiceModelToDb.updateCategoryEntity( customer, smCustomer );
			customer = _customerService.updateCustomer( customer );
			
			CustomerResponse response = new CustomerResponse( );
			response.setSMCustomer( DbToServiceModel.getSMCustomer( customer ) );
			response.setStatus( "ok, updated");
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
	
	@RequestMapping( value = "/{id_warehouse}/customers/{id_customer}/orders", method = RequestMethod.GET )
	public @ResponseBody Response getAllOrdersforCustomer( @PathVariable( "id_warehouse" ) Integer id_warehouse,
														@PathVariable( "id_customer" ) Integer id_customer)
	{
		try
		{
			List< Order > ordersList = _customerService.findByIdCustomer( id_customer ).getOrders( );
			SMOrderItem smOrderItem = new SMOrderItem( );
			List< SMOrderItem > smOrderItemList = new ArrayList< SMOrderItem >( );
			for ( Order order : ordersList )
			{
				for ( OrderItem item : order.getItems( ) )
				{
					smOrderItem = DbToServiceModel.getSMOrderItem( item );
					smOrderItemList.add( smOrderItem );
				}
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
	
	@RequestMapping( value = "/{id_warehouse}/customers/{id_customer}/orders/{id_order}",
			 method = RequestMethod.DELETE,
			 produces = "application/json",
			 consumes = "application/json" )
	public @ResponseBody Response deleteCustomerOrder( @PathVariable( "id_warehouse" ) Integer id_warehouse,
											  			   @PathVariable( "id_customer" ) Integer id_customer,
											  			   @PathVariable( "id_order" ) Integer id_order)
	{
		try
		{
			List< Order > ordersList = _customerService.findByIdCustomer( id_customer ).getOrders( );
			Order order = _customerService.findByIdOrder( id_order );
			for ( Order item : ordersList )
			{
				if ( item.getId( ) == order.getId( ) )
				{
					
					order = _customerService.deleteOrder( id_order );
				}

			}
			OrderResponse response = new OrderResponse( );
			response.setStatus("OK, order deleted"); 
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
}	
	