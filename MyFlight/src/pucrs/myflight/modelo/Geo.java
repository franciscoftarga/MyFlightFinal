package pucrs.myflight.modelo;

public class Geo {
	private double latitude;
	private double longitude;
	
	public Geo(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}

	public double distancia(Geo p2) {
		return distanciaEntrePontos(this, p2);
	}
	
	public static double distanciaEntrePontos(Geo p1, Geo p2) {
		double lat1 = Math.toRadians(p1.getLatitude());
		double lat2 = Math.toRadians(p2.getLatitude());

		double dLongitude = Math.toRadians(p2.getLongitude() - p1.getLongitude());
		double dLatitude = lat2 - lat1;

		double latSin = Math.pow(Math.sin(dLatitude / 2), 2);
		double longSin = Math.pow(Math.sin(dLongitude / 2), 2);

		double r = 6371;
		double root = Math.sqrt(latSin + longSin * Math.cos(lat1) * Math.cos(lat2));
		return 2 * r * Math.asin(root);
	}

	public boolean equals(Geo loc) {
		return this.latitude == loc.latitude &&
			   this.longitude == loc.longitude;
	}

	@Override
	public String toString() {
		return String.format("Latitude: %f, Longitude: %f", latitude, longitude);
	}
}
