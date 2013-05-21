package com.google.condesaroma;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;

public class MapActivity extends SherlockActivity implements OnClickListener {

	private static final String[] KML_FILES = {
			"http://fabricaonline.mx/CorredorCulturalRomaCondesa10.kml",
			"http://fabricaonline.mx/CorredorGastronmicoRomaCondesa.kml",
			"http://fabricaonline.mx/CorredorInfantilRomaCondesa10.kml",
			"http://fabricaonline.mx/CorredorAmbientalRomaCondesa.kml" };

	private Button culturalButton, gastroButton, infanButton, envirButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_routes);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		culturalButton = (Button) findViewById(R.id.cultural_button);
		gastroButton = (Button) findViewById(R.id.gastro_button);
		infanButton = (Button) findViewById(R.id.infa_button);
		envirButton = (Button) findViewById(R.id.enviro_button);

	}

	@Override
	protected void onResume() {
		super.onResume();
		culturalButton.setOnClickListener(MapActivity.this);
		gastroButton.setOnClickListener(MapActivity.this);
		infanButton.setOnClickListener(MapActivity.this);
		envirButton.setOnClickListener(MapActivity.this);
	}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cultural_button:
			routeMapWithKmlFile(KML_FILES[0]);
			break;
		case R.id.gastro_button:
			routeMapWithKmlFile(KML_FILES[1]);
			break;
		case R.id.infa_button:
			routeMapWithKmlFile(KML_FILES[2]);
			break;
		case R.id.enviro_button:
			routeMapWithKmlFile(KML_FILES[3]);
			break;
		default:
			break;
		}
	}

	private void routeMapWithKmlFile(String url) {
		Intent myIntent = new Intent(android.content.Intent.ACTION_VIEW,
				Uri.parse("geo:0,0?q=" + url));
		myIntent.setClassName("com.google.android.apps.maps",
				"com.google.android.maps.MapsActivity");
		startActivity(myIntent);
	}
}
