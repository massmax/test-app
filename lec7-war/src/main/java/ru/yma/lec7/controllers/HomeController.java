package ru.yma.lec7.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ru.yma.lec7.entities.Order;
import ru.yma.lec7.entities.OrderItem;
import ru.yma.lec7.entities.Product;
import ru.yma.lec7.entities.Warehouse;
import ru.yma.lec7.serviceModel.DbToServiceModel;
import ru.yma.lec7.serviceModel.object.SMOrderItem;
import ru.yma.lec7.serviceModel.object.SMProduct;
import ru.yma.lec7.serviceModel.response.OrderResponse;
import ru.yma.lec7.serviceModel.response.ProductsResponse;
import ru.yma.lec7.serviceModel.response.Response;
import ru.yma.lec7.services.ICustomerService;
import ru.yma.lec7.services.IProductService;
import ru.yma.lec7.services.IWarehouseService;

@Controller
public class HomeController
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
	
	@RequestMapping( value = "/index", method = RequestMethod.GET )
	public ModelAndView index( )
	{
		List< Warehouse > warehouses = _warehouseService.getAll( );
		ModelAndView view = new ModelAndView( "index" );
		view.addObject( "title", "Warehouse" );
		view.addObject( "greeting", "Imitation warehouse" );
		
		view.addObject( "select", "Select warehouse" );
		view.addObject( "listwarehouses", warehouses );
		return view;
	}
	
	
	@RequestMapping( value = "/warehouse/{id_warehouse}/getallproduct", method = RequestMethod.GET )
	public ModelAndView getAllProduct( @PathVariable( "id_warehouse" ) Integer id_warehouse )
	{
		List< Product > products = _warehouseService.findById( id_warehouse ).getProducts( );
		List< SMProduct > smProductList = new ArrayList< SMProduct >( ) ;
		for ( Product product : products )
		{
			SMProduct smProduct = DbToServiceModel.getSMProduct( product );
			smProductList.add( smProduct );
		}
		
		ModelAndView view = new ModelAndView( "getallproduct" );
		view.addObject( "title", "Warehouse");
		view.addObject( "greeting", "Imitation warehouse" );
		
		view.addObject( "select", "List products" );
		view.addObject("smproductlist", smProductList);
		return view;
	}
	
	@RequestMapping( value = "/warehouse/{id_warehouse}/getproduct", method = RequestMethod.GET )
	public ModelAndView getProductList( @PathVariable( "id_warehouse" ) Integer id_warehouse )
	{
		List< Product > productsList = _warehouseService.findById( id_warehouse ).getProducts( );
		
		ModelAndView view = new ModelAndView( "getproduct" );
		view.addObject( "title", "Warehouse" );
		view.addObject( "greeting", "Imitation warehouse" );		
		view.addObject( "select", "Select a product" );
		view.addObject( "productslist", productsList );
		return view;
	}
	
	@RequestMapping( value = "/warehouse/{id_warehouse}/getproduct/{id_product}", method = RequestMethod.GET, produces = "application/json" )
	public @ResponseBody Response getProductJson( @PathVariable( "id_warehouse" ) Integer id_warehouse, @PathVariable( "id_product" ) Integer id_product )
	{
		Product product = _productService.findByIdProduct( id_product );
		SMProduct smProduct = DbToServiceModel.getSMProduct( product );
		
		ProductsResponse response = new ProductsResponse( );
		response.setSMProduct( smProduct );
		response.setStatus( "ok");
		
		return response;
	}
	
	@RequestMapping( value = "/warehouse/{id_warehouse}/getorder", method = RequestMethod.GET )
	public ModelAndView getOrderList( @PathVariable( "id_warehouse" ) Integer id_warehouse )
	{
		List< OrderItem > items = _warehouseService.findById( id_warehouse ).getItems( );
		List< Order > ordersList = new ArrayList< Order >( );
		for ( OrderItem item : items )
		{
			ordersList.add( _customerService.findByIdOrder( item.getOrder( ).getId( ) ) );
		}
		
		List< Order > result = new ArrayList< Order >( );
		Set< Integer > tempSet = new HashSet< Integer >( );
		for ( Order order : ordersList )
		{
		    if ( tempSet.add( order.getId( ) ) )
		    {
		        result.add( order );
		    }
		}
		
		ModelAndView view = new ModelAndView( "getorder" );
		view.addObject( "title", "Warehouse" );
		view.addObject( "greeting", "Imitation warehouse" );		
		view.addObject( "select", "Select a order (ID)" );
		view.addObject( "orderslist", result );
		return view;
	}
	
	@RequestMapping( value = "/warehouse/{id_warehouse}/getorder/{id_order}", method = RequestMethod.GET, produces = "application/json" )
	public @ResponseBody Response getOrderJson( @PathVariable( "id_warehouse" ) Integer id_warehouse, @PathVariable( "id_order" ) Integer id_order )
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
		response.setStatus("ok");
		return response;
	}
}
