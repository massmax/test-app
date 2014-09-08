package ru.yma.lec7.serviceModel.response;

import java.util.List;

//import ru.yma.lec7.serviceModel.object.SMCustomer;
import ru.yma.lec7.serviceModel.object.SMOrderItem;

public class OrderResponse extends Response
{
	private List< SMOrderItem > _orderItemList;
	private SMOrderItem _smOrderItem;
	
	public List< SMOrderItem > getOrderItemList() { return _orderItemList; }
	public void setOrderItemList(List< SMOrderItem > orderItemList) { this._orderItemList = orderItemList; }
	
	public SMOrderItem getSMOrderItem( ) { return _smOrderItem; }
	public void setSMOrderItem( SMOrderItem smOrderItem ) { this._smOrderItem = smOrderItem; }
	
	
}
