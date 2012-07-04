package com.claygregory.common.data;

import java.util.Date;

/**
 * @author Clay Gregory
 *
 */
public interface TimestampedEvent {

	/**
	 * Timestamp in ms from epoch
	 * 
	 * @return timestamp
	 */
	public long getTimestamp( );
	
	/**
	 * timestamp as localized Date
	 * 
	 * @return timestamp as Date
	 */
	public Date getTimestampAsDate( );
	
}
