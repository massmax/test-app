package ru.yma.lec7.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.yma.lec7.jms.obj.SMProductList;
import ru.yma.lec7.serviceModel.object.SMProduct;

public class InListener implements MessageListener
{
	private static final Logger log = LoggerFactory.getLogger( InListener.class );

	private Producer _producer;
	
	public Producer getProducer( ) { return _producer; }
	public void setProducer( Producer producer ) { this._producer = producer; }

	public void onMessage( Message message )
	{
		
		try
		{
			if ( ! ( message instanceof TextMessage ) )
				{
					return ;
				}
				TextMessage msg = ( TextMessage )message;
				String json = msg.getText( );
				ObjectMapper mapper = new ObjectMapper( );
				SMProductList productList = mapper.readValue( json, SMProductList.class );
				
				for ( SMProduct smProduct : productList.getProductList( ) )
				{
					log.info( smProduct.toString( ) );
				}
				_producer.put( );
		}
		catch ( Exception e )
		{
			log.error( "Fail", e );
		}
	}

}
