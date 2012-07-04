package com.claygregory.common.data.util;

import java.util.Comparator;

import com.claygregory.common.data.TimestampedEvent;

/**
 * 
 * @author Clay Gregory
 *
 */
public final class TimestampedEventComparator<T extends TimestampedEvent> implements Comparator<T> {

	public static<T extends TimestampedEvent> TimestampedEventComparator<T> instance( ) {
		return new TimestampedEventComparator<T>( false );
	}
	
	public static<T extends TimestampedEvent> TimestampedEventComparator<T> instance( boolean desc ) {
		return new TimestampedEventComparator<T>( desc );
	}

	boolean desc = false;

	private TimestampedEventComparator( boolean desc ) {
		this.desc = desc;
	}
	
	@Override
	public int compare( T e1, T e2 ) {
		return ( int ) Math.signum( e1.getTimestamp( ) - e2.getTimestamp( ) );
	}

}
