package ru.yma.lec7.serviceModel.request;

import ru.yma.lec7.serviceModel.object.SMCustomer;

public class CustomerRequest extends Request
{
	private SMCustomer _smCustomer;
	
	public void setSMCustomer( SMCustomer customer ) { _smCustomer = customer; }
	public SMCustomer getSMCustomer( ) { return _smCustomer; }
}
