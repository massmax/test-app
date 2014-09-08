package ru.yma.lec7.serviceModel.response;

import java.util.List;

import ru.yma.lec7.serviceModel.object.SMProduct;

public class ProductsResponse extends Response
{
	private List< SMProduct > _products;
	private SMProduct _smProduct;
	
	public void setSMProductsList( List< SMProduct > products ) { _products = products; }
	public List< SMProduct > getSMProductsList( ) { return _products; }
	
	public void setSMProduct( SMProduct smProduct ) { this._smProduct = smProduct; }
	public SMProduct getSMProduct( ) { return _smProduct; }
	
}
