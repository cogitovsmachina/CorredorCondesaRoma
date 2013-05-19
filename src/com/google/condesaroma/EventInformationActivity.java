package com.google.condesaroma;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;

public class EventInformationActivity extends SherlockActivity {

	private String[] cardInformation;
	private TextView eventName, eventSchedule, eventBroker, eventDescription;
	private ShareActionProvider shareActionProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_information);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		cardInformation = getIntent().getExtras().getStringArray("cc");
		Log.d("CARD_INFORMATION", cardInformation[0]);
		eventName = (TextView) findViewById(R.id.event_name);
		eventSchedule = (TextView) findViewById(R.id.event_schedule);
		eventBroker = (TextView) findViewById(R.id.event_broker);
		eventDescription = (TextView) findViewById(R.id.event_description);

	}

	@Override
	protected void onResume() {
		super.onResume();
		eventName.setText(cardInformation[0]);
		eventBroker.setText(cardInformation[3]);
		eventDescription.setText(cardInformation[7]);
		eventSchedule.setText(cardInformation[6]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getSupportMenuInflater();
		menuInflater.inflate(R.menu.events, menu);
		MenuItem item = menu.findItem(R.id.share_event);
		shareActionProvider = (ShareActionProvider) item.getActionProvider();
		shareActionProvider.setShareIntent(setShareIntent());
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(EventInformationActivity.this,
					EventsActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;

		default:
			return false;
		}
	}

	private Intent setShareIntent() {
		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_TEXT, "#CorredorPresenta  \n"
				+ cardInformation[0] + "\n" + cardInformation[6]);
		shareIntent.setType("text/*");

		return shareIntent;
	}

}
