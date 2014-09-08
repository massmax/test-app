package ru.yma.lec7.jms.obj;

public class RequestOutQueue
{
	private String _type;
	private WarehouseIP _warehouseIP;
	private String _limit;
	
	public String getType( ) { return _type; }
	public void setType( String type ) { this._type = type; }
	
	public WarehouseIP getWarehouseIP( ) { return _warehouseIP; }
	public void setWarehouseIP( WarehouseIP warehouseIP ) { this._warehouseIP = warehouseIP; }
	
	public String getLimit( ) { return _limit; }
	public void setLimit( String limit ) { this._limit = limit; }
}
