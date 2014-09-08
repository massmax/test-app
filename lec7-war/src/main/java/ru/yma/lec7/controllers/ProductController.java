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


import ru.yma.lec7.entities.Category;
import ru.yma.lec7.entities.Product;
import ru.yma.lec7.entities.Warehouse;
import ru.yma.lec7.serviceModel.DbToServiceModel;
import ru.yma.lec7.serviceModel.ServiceModelToDb;
import ru.yma.lec7.serviceModel.object.SMProduct;
import ru.yma.lec7.serviceModel.request.ProductRequest;
import ru.yma.lec7.serviceModel.response.ErrorResponse;
import ru.yma.lec7.serviceModel.response.ProductsResponse;
import ru.yma.lec7.serviceModel.response.Response;
import ru.yma.lec7.services.IProductService;
import ru.yma.lec7.services.IWarehouseService;

@Controller
@RequestMapping( "/warehouses" )
public class ProductController
{	
	@Autowired
	@Qualifier( "productService" )
	private IProductService _productService;
	
	@Autowired
	@Qualifier( "warehouseService" )
	private IWarehouseService _warehouseService;
		
	@RequestMapping( value = "/{id_warehouse}/products", method = RequestMethod.GET )
	public @ResponseBody Response getAllProducts( @PathVariable( "id_warehouse" ) Integer id_warehouse )
	{
		try
		{
			List< Product > products = _warehouseService.findById( id_warehouse ).getProducts( );
			List< SMProduct > smProductList = new ArrayList< SMProduct >( ) ;
			for ( Product product : products )
			{
				SMProduct smProduct = DbToServiceModel.getSMProduct( product );
				smProductList.add( smProduct );
			}
			ProductsResponse response = new ProductsResponse( );
			response.setSMProductsList( smProductList );
			
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
	
	@RequestMapping( value = "/{id_warehouse}/products",
					 method = RequestMethod.POST,
					 produces = "application/json",
					 consumes = "application/json" )
	public @ResponseBody Response addProduct( @PathVariable( "id_warehouse" ) Integer id_warehouse,
														 @RequestBody ProductRequest request )
	{
		try
		{			
			SMProduct smProduct = request.getSMProduct( );						
			Category category = _productService.findByNameCategory( smProduct.getCategory( ) );
			Warehouse warehouse = _warehouseService.findById( id_warehouse );
			Product product = ServiceModelToDb.createProductEntity( smProduct, category, warehouse );			
			product = _productService.createProduct( product );
			
			ProductsResponse response = new ProductsResponse( );
			response.setSMProduct( DbToServiceModel.getSMProduct( product ) );
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
	
	@RequestMapping( value = "/{id_warehouse}/products/{id_product}",
					 method = RequestMethod.DELETE,
					 produces = "application/json",
					 consumes = "application/json" )
	public @ResponseBody Response deleteProduct( @PathVariable( "id_warehouse" ) Integer id_warehouse,
												 @PathVariable( "id_product" ) Integer id_product)
	{
		try
		{
			Product product = _productService.findByIdProduct( id_product );			
			List< Product > listProducts = _warehouseService.findById( id_warehouse ).getProducts( );
			SMProduct smProduct = null;
			for ( Product p : listProducts )
			{
				if ( product.getId( ) == p.getId( ) )
				{
					smProduct =  DbToServiceModel.getSMProduct( product );
					product = _productService.deleteProduct( id_product );
				}
			}			
			ProductsResponse response = new ProductsResponse( );
			response.setSMProduct(smProduct);
			response.setStatus( "ok, product deleted " );			
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
	
	@RequestMapping( value = "/{id_warehouse}/products/{id_product}",
					 method = RequestMethod.PUT,
					 produces = "application/json",
					 consumes = "application/json" )
	public @ResponseBody Response updateProduct( @PathVariable( "id_warehouse" ) Integer id_warehouse,
														  @PathVariable( "id_product" ) Integer id_product,
														  @RequestBody ProductRequest request )
	{
		try
		{
			Product product = _productService.findByIdProduct( id_product );
			List< Product > listProducts = _warehouseService.findById( id_warehouse ).getProducts( );
			SMProduct smProduct = request.getSMProduct( );
			for ( Product p : listProducts )
			{
				if ( product.getId( ) == p.getId( ) )
				{
					product = ServiceModelToDb.updateProductEntity( product, smProduct );
					product = _productService.updateProduct( product );
				}
			}	
			ProductsResponse response = new ProductsResponse( );
			response.setSMProduct( DbToServiceModel.getSMProduct( product ) );
			response.setStatus( "ok, product updated" );			
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
	