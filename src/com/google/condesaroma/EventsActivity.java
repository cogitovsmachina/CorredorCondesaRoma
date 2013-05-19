package com.google.condesaroma;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.condesaroma.util.FragmentPager;
import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

public class EventsActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		FragmentPager fragmentPager = new FragmentPager(
				getSupportFragmentManager());
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(fragmentPager);

		TitlePageIndicator indicator = (TitlePageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);
		setCustomStyleIndicator(indicator);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intentHome = new Intent(EventsActivity.this,
					MainActivity.class);
			intentHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentHome);
		default:
			return false;
		}
	}

	private void setCustomStyleIndicator(TitlePageIndicator indicator) {
		float density = getResources().getDisplayMetrics().density;
		indicator.setBackgroundColor(0x41FFFB73);
		indicator.setFooterColor(0xFFFFFF);
		indicator.setFooterLineHeight(2 * density); // 1dp
		indicator.setFooterIndicatorHeight(3 * density); // 3dp
		indicator.setFooterIndicatorStyle(IndicatorStyle.Underline);
		indicator.setTextColor(0xFFFFFFFF);
		indicator.setSelectedColor(0xFF000000);
		indicator.setSelectedBold(true);
	}

}
