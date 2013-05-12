package com.google.condesaroma;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

public class MapActivity extends Activity {
	private static final String KML_FILE = "http://fabricaonline.mx/CorredorCulturalRomaCondesa10.kml";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setTitle("Map Prototype");
		final Intent myIntent = new Intent(android.content.Intent.ACTION_VIEW,
				Uri.parse("geo:0,0?q="+KML_FILE));
				startActivity(myIntent);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
