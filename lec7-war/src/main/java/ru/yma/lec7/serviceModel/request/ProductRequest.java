package ru.yma.lec7.serviceModel.request;

import ru.yma.lec7.serviceModel.object.SMProduct;

public class ProductRequest
{
	private SMProduct _product;
	
	public void setSMProduct( SMProduct product ) { _product = product; }
	public SMProduct getSMProduct( ) { return _product; }
}
