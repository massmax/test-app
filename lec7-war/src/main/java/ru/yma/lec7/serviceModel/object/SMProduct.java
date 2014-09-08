package ru.yma.lec7.serviceModel.object;

public class SMProduct
{
	private String _name;
	
	private String _description;
	
	private float _price;
	
	private float _quantity;
	
	private float _discount;
	
	private String _category;

	public String getName( ) { return _name; }

	public void setName( String name ) { this._name = name; }

	public String getDescription( ) { return _description; }

	public void setDescription( String description ) { this._description = description; }

	public float getPrice( ) { return _price; }

	public void setPrice( float price ) { this._price = price; }

	public float getQuantity() { return _quantity; }

	public void setQuantity( float quantity ) { this._quantity = quantity; }

	public float getDiscount( ) { return _discount; }

	public void setDiscount( float discount ) { this._discount = discount; }

	public String getCategory( ) { return _category; }

	public void setCategory( String category ) { this._category = category; }

	@Override
	public String toString( )
	{
		return "[ name: "+ _name + ", description = " + _description + ", price = " + _price + ", quantity = " + _quantity + ", discount = " + _discount + "]"; 
	}
	
	
			
}
