package mx.fabricaonline.corredorromacondesa.ui;

import mx.fabricaonline.corredorromacondesa.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PublicationsActivity extends ActionBarActivity {
	// private static final String PLUS_URL =
	// "https://plus.google.com/+ccromacondesa/posts";
	private static final String PLUS_MOBILE_URL = "https://plus.google.com/app/basic/+ccromacondesa/posts?cbp=1js8p1gj7rgrz&sview=25";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		WebView webView = (WebView) findViewById(R.id.webview);
		// webView.getSettings().setJavaScriptEnabled(true); //could provoke
		// vulnerabilities
		webView.getSettings().setBuiltInZoomControls(true);
		webView.setWebViewClient(new MyWebViewClient());
		webView.loadUrl(PLUS_MOBILE_URL);

	}

	private class MyWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// This is my web site, so do not override; let my WebView load
			// the page

			if (Uri.parse(url).getHost().equals("plus.google.com")) {
				return false;
			}
			// Otherwise, the link is not for a page on my site, so launch
			// another Activity that handles URLs
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			startActivity(intent);
			return true;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent home = new Intent(PublicationsActivity.this,
					MainActivity.class);
			startActivity(home);
			return true;

		default:
			return false;
		}
	}

}
