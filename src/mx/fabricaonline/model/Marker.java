package mx.fabricaonline.model;

import java.io.Serializable;

public class Marker implements Serializable{

	private static final long serialVersionUID = 1L;
	private static String name ="";
	private static double latitude;
	private static double longitude;
	
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		Marker.name = name;
	}
	public static double getLatitude() {
		return latitude;
	}
	public static void setLatitude(double latitude) {
		Marker.latitude = latitude;
	}
	public static double getLongitude() {
		return longitude;
	}
	public static void setLongitude(double longitude) {
		Marker.longitude = longitude;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
