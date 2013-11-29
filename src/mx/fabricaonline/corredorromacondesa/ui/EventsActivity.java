package mx.fabricaonline.corredorromacondesa.ui;

import mx.fabricaonline.corredorromacondesa.R;
import mx.fabricaonline.corredorromacondesa.adapter.FragmentPager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.view.MenuItem;

public class EventsActivity extends ActionBarActivity {
	
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		
		ActionBar actionBar =  getSupportActionBar();
		getSupportActionBar().setIcon(R.drawable.logo11_navbar_);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		FragmentPager fragmentPager = new FragmentPager(
				getSupportFragmentManager());
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(fragmentPager);
		
		ActionBar.TabListener tabListener = new TabListener() {
			
			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction arg1) {
				pager.setCurrentItem(tab.getPosition());
				
			}
			
			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
		};
		
		actionBar.addTab(actionBar.newTab().setText("Condesa").setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText("Roma").setTabListener(tabListener));
		
		pager.setOnPageChangeListener(
	            new ViewPager.SimpleOnPageChangeListener() {
	                @Override
	                public void onPageSelected(int position) {
	                    getSupportActionBar().setSelectedNavigationItem(position);
	                }
	            });
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
}
