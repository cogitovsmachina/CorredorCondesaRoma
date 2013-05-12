package com.google.condesaroma;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HomeActivity extends Activity {
	private static final String PLUS_URL = "https://plus.google.com/+ccromacondesa/posts";
	private static final String PLUS_MOBILE_URL = "https://plus.google.com/app/basic/+ccromacondesa/posts?cbp=1js8p1gj7rgrz&sview=25";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setTitle("Webview Prototype");

		WebView webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
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

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
