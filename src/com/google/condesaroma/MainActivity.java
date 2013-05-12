package com.google.condesaroma;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
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
}
