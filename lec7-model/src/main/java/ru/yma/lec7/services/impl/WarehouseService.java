package ru.yma.lec7.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.yma.lec7.entities.Warehouse;
import ru.yma.lec7.repositories.WarehouseRepository;
import ru.yma.lec7.services.IWarehouseService;

public class WarehouseService implements IWarehouseService
{
	private static final Logger log = LoggerFactory.getLogger( WarehouseService.class );
	
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	@Transactional
	public Warehouse create( Warehouse warehouse )
	{
		List< Warehouse > listWarehouses = warehouseRepository.findByNameWarehouse( warehouse.getName( ) );
		if( ! listWarehouses.isEmpty( ) )
		{
			if ( listWarehouses.get( 0 ).getCapacity( ) == warehouse.getCapacity( ) )
			{
				log.info( " This Warehouse has" );
				return listWarehouses.get( 0 );
			}
		}		
		log.info( "Warehouse " + warehouse.getName( ) + " created " );
		return warehouseRepository.save( warehouse );
	}

	@Transactional( rollbackFor = Exception.class )
	public Warehouse delete( Integer id )
			throws Exception
	{
		Warehouse deletedWarehouse = warehouseRepository.findOne( id );
		if ( deletedWarehouse == null )
		{
			throw new Exception( " Warehouse not found "); 
		}
		log.info( "Warehouse " + deletedWarehouse.getName( ) + " deleted " );
		warehouseRepository.delete( deletedWarehouse );
		return deletedWarehouse;
	}

	@Transactional
	public List< Warehouse > getAll( )
	{
		return warehouseRepository.findAll( );
	}

	@Transactional( rollbackFor = Exception.class )
	public Warehouse update( Warehouse warehouse )
			throws Exception
	{
		Warehouse updateWarehouse = warehouseRepository.findOne( warehouse.getId( ) );
		if ( updateWarehouse == null )
		{
			throw new Exception( "Warehouse not found ");
		}
		updateWarehouse.setName( warehouse.getName( ) );
		updateWarehouse.setCapacity( warehouse.getCapacity( ) );
		updateWarehouse.setProducts( warehouse.getProducts( ) );
		warehouseRepository.save( updateWarehouse );
		log.info( "Warehouse " + updateWarehouse.getName( ) + " updated " );
		return updateWarehouse;
	}

	@Transactional
	public Warehouse findById( Integer id )
	{		
		return warehouseRepository.findOne( id );
	}
	
	@Transactional
	public Warehouse findByNameWarehouse( String name )
	{
		List< Warehouse > list = warehouseRepository.findByNameWarehouse( name );
		return list.get( 0 );
	}
	
}
