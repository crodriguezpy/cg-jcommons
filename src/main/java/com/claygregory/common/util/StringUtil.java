package com.claygregory.common.util;

import java.util.Arrays;
import java.util.Collection;

/**
 * 
 * General purpose String utils
 * 
 * @author Clay Gregory
 *
 */
public final class StringUtil {
	
	public static boolean empty( String s ) {
		return s == null || s.trim( ).length( ) == 0;
	}
	
	public static<T extends Object> String join( String sep, T... collect ) {
		return join( sep, Arrays.asList( collect ) );
	}

	public static String join( String sep, Collection<?> collect ) {
		
		StringBuilder sb = new StringBuilder( );
		
		boolean first = true;
		for ( Object o : collect ) {
			
			if ( !first )
				sb.append( sep );
			else
				first = false;
		
			sb.append( o );
		}
		
		return sb.toString( );
	}
}
