package ru.yma.lec7.serviceModel.request;

import ru.yma.lec7.serviceModel.object.SMOrderItem;

public class OrderItemRequest
{
	private SMOrderItem _smOrderItem;
	
	public SMOrderItem getSmOrderItem( ) { return _smOrderItem; }
	public void setSmOrderItem( SMOrderItem smOrderItem ) { this._smOrderItem = smOrderItem; }
}
