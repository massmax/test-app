package ru.yma.lec7.services;

import java.util.List;

import ru.yma.lec7.entities.Warehouse;

public interface IWarehouseService
{
	public Warehouse create( Warehouse warehouse );
	public Warehouse delete( Integer id ) throws Exception;
	public List< Warehouse > getAll( );
	public Warehouse update( Warehouse warehouse ) throws Exception;
	public Warehouse findById( Integer id );
	public Warehouse findByNameWarehouse( String name );
}
