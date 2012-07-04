package com.claygregory.common.net;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.claygregory.common.collection.HashMultiMap;
import com.claygregory.common.collection.MultiMap;
import com.claygregory.common.util.StringUtil;

/**
 * 
 * @author Clay Gregory
 *
 */
public final class URLBuilder {
	
	private static final String DEFAULT_PROTO = "http";
	
	private static final String PROTO_SEPARATOR = "://";
	
	private static final String PORT_SEPARATOR = ":";

	private static final String QUERY_SEPARATOR = "?";

	private static final String REF_SEPARATOR = "#";
	
	private String proto;
	
	private String host;
	
	private Integer port;
	
	private String path;
	
	private MultiMap<String,String> query = new HashMultiMap<String,String>( );
	
	private String ref;
	
	public static URLBuilder create( ) {
		return new URLBuilder( );
	}
	
	public URLBuilder( ) {
		//no-args constructor
	}
	
	public static URLBuilder create( String url ) throws MalformedURLException {
		return new URLBuilder( url );
	}
	
	public URLBuilder( String url ) throws MalformedURLException {
		this( new URL( url ) );
	}
	
	public static URLBuilder create( URL url ) {
		return new URLBuilder( url );
	}
	
	public URLBuilder( URL url ) {
		this.proto = url.getProtocol( );
		this.host = url.getHost( );
		this.port = url.getPort( );
		this.path = url.getPath( );
		this.query = parseQueryString( url.getQuery( ) );
		this.ref = url.getRef( );
	}
	
	public URLBuilder proto( String proto ) {
		this.proto = proto;
		return this;
	}
	
	public String proto( ) {
		return this.proto;
	}
	
	public URLBuilder host( String host ) {
		this.host = host;
		return this;
	}
	
	public String host( ) {
		return this.host;
	}
	
	public URLBuilder port( Integer port ) {
		this.port = port;
		return this;
	}
	
	public Integer port( ) {
		return this.port;
	}
	
	public URLBuilder path( String path ) {
		this.path = path;
		return this;
	}
	
	public String path( ) {
		return this.path;
	}
	
	public URLBuilder query( MultiMap<String,String> query ) {
		this.query = query;
		return this;
	}
	
	public URLBuilder query( String queryString ) {
		this.query = parseQueryString( queryString );
		return this;
	}
	
	public MultiMap<String,String> query( ) {
		return this.query;
	}
	
	public URLBuilder queryParam( String key, String... values ) {
		synchronized( this ) {
			this.query.remove( key );
			if ( values != null )
				this.query.put( key, Arrays.asList( values ) );
		}
		return this;
	}
	
	public URLBuilder ref( String ref ) {
		this.ref = ref;
		return this;
	}
	
	public String ref( ) {
		return this.ref;
	}

	public URL buildURL( ) throws MalformedURLException {
		
		StringBuilder urlString = new StringBuilder( );
		urlString.append( this.proto( ) == null ? DEFAULT_PROTO : this.proto( ) );
		urlString.append( PROTO_SEPARATOR );
		urlString.append( this.host( ) );
		if ( port != null && port > 0 )
			urlString.append( PORT_SEPARATOR + port );
		
		if ( this.path( ) != null )
			urlString.append( path );
		
		if ( this.query( ) != null ) {
			String queryString = buildQuery( this.query( ) );
			if ( queryString != null ) {
				urlString.append( QUERY_SEPARATOR );
				urlString.append( queryString );
			}
		}
		
		if ( this.ref( ) != null )
			urlString.append( REF_SEPARATOR + ref );
		
		return new URL( urlString.toString( ) );
	}
	
	private static String buildQuery( MultiMap<String,String> query ) {
		
		if ( query == null || query.isEmpty( ) )
			return null;
		
		StringBuilder sb = new StringBuilder( );
		boolean first = true;
		for ( String key : query.keySet( ) ) {
			for ( String value : query.get( key ) ) {
			
				if ( !first )
					sb.append( '&' );
				
				sb.append( key );
				if ( value != null ) {
					sb.append( '=' );
					try {
						sb.append( URLEncoder.encode( value, Charset.defaultCharset( ).name( ) ) );
					} catch( UnsupportedEncodingException e ) {
						throw new RuntimeException( e );
					}
				}
				first = false;
			}
		}
		
		return sb.toString( );
	}
	
	private static MultiMap<String,String> parseQueryString( String query ) {
		
		MultiMap<String,String> qMap = new HashMultiMap<String,String>( );
		if ( StringUtil.empty( query ) )
			return qMap;
		
		for ( String param : query.split( "&" ) ) {
			String[ ] sParam = param.split( "=" );
		
			qMap.put( sParam[ 0 ], sParam.length == 2 ? sParam[ 1 ] : null );	
		}
			
		
		return qMap;
	}
}
