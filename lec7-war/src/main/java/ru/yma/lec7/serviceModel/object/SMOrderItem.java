package ru.yma.lec7.serviceModel.object;

public class SMOrderItem
{
	private Integer _idItem;
	private Integer _idOrder;
	private String _nameProduct;
	private String _nameWarehouse;
	private Float _quantity;
	
	
	public Integer getIdItem( ) { return _idItem; }
	public void setIdItem( Integer idItem ) { this._idItem = idItem; }
	
	public Integer getIdOrder( ) { return _idOrder; }
	public void setIdOrder( Integer idOrder ) { this._idOrder = idOrder; }
	
	public String getNameProduct( ) { return _nameProduct; }
	public void setNameProduct( String nameProduct ) { this._nameProduct = nameProduct; }
	
	public String getNameWarehouse( ) { return _nameWarehouse; }
	public void setNameWarehouse( String nameWarehouse ) { this._nameWarehouse = nameWarehouse; }
	
	public Float getQuantity( ) { return _quantity; }
	public void setQuantity( Float quantity ) { this._quantity = quantity; }
	
}
