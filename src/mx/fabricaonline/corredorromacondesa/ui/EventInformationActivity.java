package mx.fabricaonline.corredorromacondesa.ui;

import mx.fabricaonline.corredorromacondesa.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class EventInformationActivity extends ActionBarActivity {

	private String[] cardInformation;
	private TextView eventName, eventSchedule, eventBroker, eventDescription,
			eventLink;
	//private ActionProvider shareActionProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_information);
		
		ActionBar actionBar = getSupportActionBar();

		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setIcon(R.drawable.logo11_navbar_);

		cardInformation = getIntent().getExtras().getStringArray("cc");
		Log.d("CARD_INFORMATION", cardInformation[0]);
		eventName = (TextView) findViewById(R.id.event_name);
		eventSchedule = (TextView) findViewById(R.id.event_schedule);
		eventBroker = (TextView) findViewById(R.id.event_broker);
		eventDescription = (TextView) findViewById(R.id.event_description);
		eventLink = (TextView) findViewById(R.id.link);

	}

	@Override
	protected void onResume() {
		super.onResume();
		eventName.setText(cardInformation[0]);
		eventBroker.setText(cardInformation[3]);
		eventDescription.setText(cardInformation[7]);
		eventSchedule.setText(getResources().getString(R.string.event_schedule)
				+ "\n" + cardInformation[6]);
		eventLink.setText(cardInformation[4]);
		Linkify.addLinks(eventLink, Linkify.ALL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.events, menu);
		MenuItem item = menu.findItem(R.id.share_event);
		// shareActionProvider = (ShareActionProvider) item.getActionProvider();
		// TODO: MAKE SURE HOW TO DO THIS THING
		// shareActionProvider = item.getActionProvider();
		// shareActionProvider.subUiVisibilityChanged(true);
		// shareActionProvider.setShareIntent(setShareIntent());
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.onBackPressed();
			return true;

		default:
			return false;
		}
	}

	private Intent setShareIntent() {
		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);

		shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
				"#micorredor  \n" + cardInformation[0] + "\n"
						+ cardInformation[4] + "\n" + cardInformation[6]);
		shareIntent.setType("text/*");

		return shareIntent;
	}

}
