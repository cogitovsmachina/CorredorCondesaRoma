package mx.fabricaonline.corredorromacondesa.ui;

import java.io.IOException;
import java.io.InputStream;

import mx.fabricaonline.corredorromacondesa.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

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

public class MapActivity extends ActionBarActivity implements LocationListener,
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener {

	private SupportMapFragment map;
	private LocationClient locationClient;
	private double lat;
	private double lon;
	private InputStream stream;
	private JSONArray kidsArray;
	private JSONArray environmentArray;
	private JSONArray artArray;
	private JSONArray eventArray;
	private JSONArray foodArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		getSupportActionBar().setIcon(R.drawable.logo11_navbar_);
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
		boolean on = ((ToggleButton) view).isChecked();
		if (on) {
			String jsonEnvironment = getTextFromAssets("ambiental.txt");
			try {
				JSONObject environment = new JSONObject(jsonEnvironment);
				environmentArray = environment.getJSONArray("Ambiental");

				for (int i = 0; i < environmentArray.length(); i++) {
					String name = environmentArray.getJSONObject(i).getString(
							"name");
					String latitude = environmentArray.getJSONObject(i)
							.getString("latitude");
					String longitude = environmentArray.getJSONObject(i)
							.getString("longitude");

					drawMarkers(name, latitude, longitude,
							BitmapDescriptorFactory.HUE_GREEN);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			map.getMap().clear();
		}

	}

	public void displayArtMarkers(View view) {
		boolean on = ((ToggleButton) view).isChecked();
		if (on) {

			String jsonArt = getTextFromAssets("arte.txt");
			try {
				JSONObject art = new JSONObject(jsonArt);
				artArray = art.getJSONArray("Arte");

				for (int i = 0; i < artArray.length(); i++) {
					String name = artArray.getJSONObject(i).getString("name");
					String latitude = artArray.getJSONObject(i).getString(
							"latitude");
					String longitude = artArray.getJSONObject(i).getString(
							"longitude");

					drawMarkers(name, latitude, longitude,
							BitmapDescriptorFactory.HUE_ORANGE);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			map.getMap().clear();
		}

	}

	public void displayEventsMarkers(View view) {
		boolean on = ((ToggleButton) view).isChecked();
		if (on) {
			String jsonEvents = getTextFromAssets("eventos.txt");
			try {
				JSONObject event = new JSONObject(jsonEvents);
				eventArray = event.getJSONArray("Eventos");

				for (int i = 0; i < eventArray.length(); i++) {
					String name = eventArray.getJSONObject(i).getString("name");
					String latitude = eventArray.getJSONObject(i).getString(
							"latitude");
					String longitude = eventArray.getJSONObject(i).getString(
							"longitude");

					drawMarkers(name, latitude, longitude,
							BitmapDescriptorFactory.HUE_RED);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			map.getMap().clear();
		}

	}

	public void displayFoodMarkers(View view) {
		boolean on = ((ToggleButton) view).isChecked();
		if (on) {
			String jsonFood = getTextFromAssets("gastronomico.txt");
			try {
				JSONObject food = new JSONObject(jsonFood);
				foodArray = food.getJSONArray("Gastronomico");

				for (int i = 0; i < foodArray.length(); i++) {
					String name = foodArray.getJSONObject(i).getString("name");
					String latitude = foodArray.getJSONObject(i).getString(
							"latitude");
					String longitude = foodArray.getJSONObject(i).getString(
							"longitude");

					drawMarkers(name, latitude, longitude,
							BitmapDescriptorFactory.HUE_YELLOW);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			map.getMap().clear();
		}

	}

	public void displayKidsMarkers(View view) {
		boolean on = ((ToggleButton) view).isChecked();
		if (on) {

			String jsonKids = getTextFromAssets("infantil.txt");
			try {
				JSONObject kids = new JSONObject(jsonKids);
				kidsArray = kids.getJSONArray("Infantil");

				for (int i = 0; i < kidsArray.length(); i++) {
					String name = kidsArray.getJSONObject(i).getString("name");
					String latitude = kidsArray.getJSONObject(i).getString(
							"latitude");
					String longitude = kidsArray.getJSONObject(i).getString(
							"longitude");

					drawMarkers(name, latitude, longitude,
							BitmapDescriptorFactory.HUE_CYAN);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			map.getMap().clear();
		}

	}

	private void drawMarkers(String name, String latitude, String longitude,
			float color) {

		LatLng position = new LatLng(Double.parseDouble(latitude),
				Double.parseDouble(longitude));
		map.getMap().addMarker(
				new MarkerOptions().position(position).title(name)
						.icon(BitmapDescriptorFactory.defaultMarker(color)));

	}

	private String getTextFromAssets(String assetName) {
		String text;
		try {
			InputStream is = getAssets().open(assetName);
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
