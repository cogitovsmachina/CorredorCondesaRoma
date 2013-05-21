package mx.fabricaonline.corredorromacondesa.adapter;

import mx.fabricaonline.corredorromacondesa.fragment.EventFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentPager extends FragmentPagerAdapter {

	protected static final String[] CONTENT = new String[] { "Condesa", "Roma" };

	public FragmentPager(FragmentManager fm) {
		super(fm);

	}

	@Override
	public Fragment getItem(int arg0) {

		switch (arg0) {
		case 0:
			return EventFragment.newInstance("programacionCC.xml");
		case 1:
			return EventFragment.newInstance("programacionCR.xml");
		default:
			return null;
		}

	}

	@Override
	public CharSequence getPageTitle(int position) {
		return CONTENT[position];
	}

	@Override
	public int getCount() {
		return CONTENT.length;
	}

}