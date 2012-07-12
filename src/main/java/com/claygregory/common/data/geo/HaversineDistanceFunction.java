package com.claygregory.common.data.geo;

import com.claygregory.common.core.DistanceFunction;

/**
 * Distance function providing the great-circle distance between two points based on 
 * the haversine formula.
 * 
 * @author Clay Gregory
 *
 */
public class HaversineDistanceFunction implements DistanceFunction<GeoLocation> {
	
	private static final int EARTH_RADIUS = 6371000;
	
	private static final HaversineDistanceFunction INSTANCE = new HaversineDistanceFunction( );
	
	private HaversineDistanceFunction( ) {
		//this is a singleton
	}
	
	public static HaversineDistanceFunction instance( ) {
		return INSTANCE;
	}

	/**
	 * 
	 * @return the distance between two <code>Geolocation</code>s in meters
	 */
	@Override
	public double distance( GeoLocation location1, GeoLocation location2 ) {
		
		double dLat = Math.toRadians( location2.getLatitude( ) - location1.getLatitude( ) );
		double dLng = Math.toRadians( location2.getLongitude( ) - location1.getLongitude( ) );
		
		double a = Math.sin( dLat / 2 ) * Math.sin( dLat / 2 ) +
			Math.cos( Math.toRadians( location1.getLatitude( ) ) ) *
			Math.cos( Math.toRadians( location2.getLatitude( ) ) ) *
			Math.sin( dLng / 2 ) *
			Math.sin( dLng / 2 );
		
		double c = 2 * Math.atan2( Math.sqrt( a ), Math.sqrt( 1 - a ) );
		
		return EARTH_RADIUS * c;
	}
}
