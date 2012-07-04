package com.claygregory.common.data.geo;

/**
 * 
 * @author Clay Gregory
 *
 */
public interface GeoLocation {

	/**
	 * 
	 * @return latitude, in decimal degrees
	 */
	public float getLatitude( );
	
	/**
	 * 
	 * @return longitude, in decimal degrees
	 */
	public float getLongitude( );
	
	/**
	 * 
	 * @return accuracy of coordinates in non-negative meters
	 */
	public Integer getAccuracy( );
}
