package mx.fabricaonline.corredorromacondesa.ui;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.view.View;
import mx.fabricaonline.corredorromacondesa.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class MapActivity extends ActionBarActivity implements LocationListener,
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener {

	private SupportMapFragment map;
	private LocationClient locationClient;
	private double lat;
	private double lon;
	private JSONArray parkingPlaces = new JSONArray();
	private InputStream stream;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		map = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		if (map.getMap() != null) {
			map.getMap().setMyLocationEnabled(true);
		}
		locationClient = new LocationClient(this, this, this);

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent homeIntent = new Intent(MapActivity.this, MainActivity.class);
			homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(homeIntent);
			return true;
		default:
			return false;
		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
	}

	@Override
	public void onConnected(Bundle bundle) {
		Location currentLocation = locationClient.getLastLocation();
		lat = currentLocation.getLatitude();
		lon = currentLocation.getLongitude();

		LatLng latLng = new LatLng(lat, lon);
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,
				15);
		// map.animateCamera(cameraUpdate);
		map.getMap().moveCamera(cameraUpdate);
	}

	@Override
	public void onDisconnected() {

	}

	@Override
	public void onLocationChanged(Location location) {
		lat = location.getLatitude();
		lon = location.getLongitude();
	}

	@Override
	protected void onStop() {
		locationClient.disconnect();
		super.onStop();
	}

	@Override
	protected void onStart() {
		locationClient.connect();
		super.onStart();
	}

	public void displayEnvironmentMarkers(View view) {
		// TODO: Validate if is checked, do something
		map.getMap()
				.addMarker(
						new MarkerOptions()
								.position(new LatLng(19.414595, -99.158935))
								.title("SAK, Taller de integración plástica")
								.icon(BitmapDescriptorFactory
										.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
		// TODO: Else, do another thing
	}

	public void displayArtMarkers(View view) {
		// TODO: IMPLEMENT SAME FUNCTIONALITY FROM PARKINGFINDER:
		String json = getTextFromResource("locations.json");
		//TODO: Parse JSON data
	}

	// map.getMap().clear();
	// }

	public void displayEventsMarkers(View view) {
		map.getMap().clear();
	}

	public void displayFoodMarkers(View view) {
		map.getMap().clear();
		// drawingRooms();
	}

	public void displayKidsMarkers(View view) {
		map.getMap().clear();
	}

	private String getTextFromResource(String string) {
		String text;
		try {
			InputStream is = getAssets().open(string);
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			text = new String(buffer);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return text;
	}

}
