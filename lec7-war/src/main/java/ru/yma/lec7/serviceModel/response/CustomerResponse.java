package ru.yma.lec7.serviceModel.response;

import java.util.List;

import ru.yma.lec7.serviceModel.object.SMCustomer;
import ru.yma.lec7.serviceModel.object.SMProduct;

public class CustomerResponse extends Response
{
	private List< SMCustomer > _customersList;
	private SMCustomer _smCustomer;
	
	public void setSMCustomersList( List< SMCustomer > customersList ) { _customersList = customersList; }
	public List< SMCustomer > getSMCustomerList( ) { return _customersList; }
	
	public void setSMCustomer( SMCustomer smCustomer ) { this._smCustomer = smCustomer; }
	public SMCustomer getSMProduct( ) { return _smCustomer; }
	
}
