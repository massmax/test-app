package ru.yma.lec7.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import ru.yma.lec7.jms.obj.RequestOutQueue;
import ru.yma.lec7.jms.obj.WarehouseIP;

public class Producer
{
	private static final Logger log = LoggerFactory.getLogger( Producer.class );
	
	
	private JmsTemplate _jmsTemplate;
	private Destination _destination;
	
	public JmsTemplate getJmsTemplate( ) { return _jmsTemplate; }
	public void setJmsTemplate( JmsTemplate jmsTemplate ) { this._jmsTemplate = jmsTemplate; }
	
	public Destination getDestination( ) { return _destination; }
	public void setDestination( Destination destination ) { this._destination = destination; }
	
	public void put( )
	{
		try
		{
			WarehouseIP war = new WarehouseIP( );
			war.setIp( "192.168.0.1" );
			war.setPort( "9000" );
			
			RequestOutQueue req = new RequestOutQueue( );
			req.setType( "product.request" );
			req.setWarehouseIP( war );
			req.setLimit( "2000" );
			ObjectMapper mapper = new ObjectMapper( );
			final String json = mapper.writeValueAsString( req );
			
			_jmsTemplate.send( _destination, new MessageCreator( )
			{
				public Message createMessage( Session session )
						throws JMSException
				{
					TextMessage message = session.createTextMessage( json );
					log.info( " Message sent to the queue " );
					return message;
				}
				
			});
		}
		catch( Exception e )
		{
			log.error( "Fail", e );
		}
	}
}
