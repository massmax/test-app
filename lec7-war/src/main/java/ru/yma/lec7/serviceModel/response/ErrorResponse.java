package ru.yma.lec7.serviceModel.response;

public class ErrorResponse extends Response
{
	private String _message;

	public void setMessage( String message ) { _message = message; }
	public String getMessage( ) { return _message; }
}
