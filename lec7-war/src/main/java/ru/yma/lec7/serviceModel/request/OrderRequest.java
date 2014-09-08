package ru.yma.lec7.serviceModel.request;

import java.util.List;

import ru.yma.lec7.serviceModel.object.SMOrderItem;

public class OrderRequest
{
	private List< SMOrderItem> _smOrderList;
	//private SMOrderItem _smOrderItem;
	private String _nameCustomer;
	
	public void setSmOrderList( List< SMOrderItem> orderList ) { this._smOrderList = orderList; }
	public List< SMOrderItem > getSmOrderList( ) { return _smOrderList; }
	
	//public SMOrderItem getSmOrderItem( ) { return _smOrderItem; }
	//public void setSmOrderItem( SMOrderItem smOrderItem ) { this._smOrderItem = smOrderItem; }
	
	public String getNameCustomer( ) { return _nameCustomer; }
	public void setNameCustomer( String nameCustomer ) {  this._nameCustomer = nameCustomer; }
	
	
}
