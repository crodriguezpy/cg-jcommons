package com.claygregory.common.util;

public final class ObjectUtil {

	public final static<T> boolean nullsafeEquals( T o1, T o2 ) {
		
		if ( o1 == null || o2 == null )
			return false;
		
		return o1.equals( o2 );
	}
}
