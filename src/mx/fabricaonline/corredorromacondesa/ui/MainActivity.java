package mx.fabricaonline.corredorromacondesa.ui;

import mx.fabricaonline.corredorromacondesa.R;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private Button youtubeButton, publicationsButton, routesButton,
			eventsButton;
	private static final String URL_YOUTUBE_CHANNEL = "http://www.youtube.com/user/CorredorRomaCondesa";
	private static final String PLUS_URL = "https://plus.google.com/+ccromacondesa/posts";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setIcon(R.drawable.logo_navbar);
		actionBar.setTitle("");
		
		youtubeButton = (Button) findViewById(R.id.youtube_button);
		publicationsButton = (Button) findViewById(R.id.publication_button);
		routesButton = (Button) findViewById(R.id.route_button);
		eventsButton = (Button) findViewById(R.id.event_button);

	}

	@Override
	protected void onResume() {
		super.onResume();
		youtubeButton.setOnClickListener(MainActivity.this);
		publicationsButton.setOnClickListener(MainActivity.this);
		routesButton.setOnClickListener(MainActivity.this);
		eventsButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.information_contact:
			informationDialog(MainActivity.this);
			return true;

		default:
			return false;
		}

	}

	private void informationDialog(Context context) {

		AlertDialog.Builder builder;
		AlertDialog alertDialog;
		// LayoutInflater inflater = (LayoutInflater) context
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// View layout = inflater.inflate(R.layout.custom_dialog,
		// (ViewGroup) findViewById(R.id.custom_dialog));

		builder = new AlertDialog.Builder(context);
		// builder.setView(layout);

		builder.setTitle(context.getResources()
				.getString(R.string.title_dialog));
		builder.setMessage(context.getResources().getString(
				R.string.message_dialog)
				+ "\n\n" + context.getResources().getString(R.string.link_page));

		alertDialog = builder.create();

		alertDialog.show();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.route_button:
			Intent routesMap = new Intent(MainActivity.this, MapActivity.class);
			startActivity(routesMap);
			break;
		case R.id.publication_button:
			googlePlusPublications(PLUS_URL);
			break;
		case R.id.event_button:
			Intent eventIntent = new Intent(MainActivity.this,
					EventsActivity.class);
			startActivity(eventIntent);
			break;
		case R.id.youtube_button:
			launcherYoutubeChannel(URL_YOUTUBE_CHANNEL);
			break;
		default:
			break;
		}
	}

	private void googlePlusPublications(String url) {

		Intent googlePlusPageLauncher = null;

		try {
			googlePlusPageLauncher = new Intent(Intent.ACTION_VIEW);
			googlePlusPageLauncher.setClassName("com.google.android.apps.plus",
					"com.google.android.apps.plus.phone.UrlGatewayActivity");
			googlePlusPageLauncher.setData(Uri.parse(url));
			startActivity(googlePlusPageLauncher);
		} catch (ActivityNotFoundException e) {
			googlePlusPageLauncher = new Intent(MainActivity.this,
					PublicationsActivity.class);
			googlePlusPageLauncher.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(googlePlusPageLauncher);
		}

	}

	private void launcherYoutubeChannel(String url) {
		Intent intent = null;
		try {
			intent = new Intent(Intent.ACTION_VIEW);
			intent.setPackage("com.google.android.youtube");
			intent.setData(Uri.parse(url));
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(url));
			startActivity(intent);
		}
	}

}
