package ru.yma.lec7.jms.obj;

import java.util.List;

import ru.yma.lec7.serviceModel.object.SMProduct;

public class SMProductList
{
	private List< SMProduct > _productList;

	public List< SMProduct > getProductList( ) { return _productList; }
	public void setProductList( List< SMProduct > productList ) { this._productList = productList; }
	
}
