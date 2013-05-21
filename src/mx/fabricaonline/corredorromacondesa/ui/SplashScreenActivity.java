package mx.fabricaonline.corredorromacondesa.ui;

import mx.fabricaonline.corredorromacondesa.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {

	private boolean actived = true;
	private int splashTime = 950;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		// -- Thread for show splash screen--
		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					int wait = 0;
					while (actived && (wait < splashTime)) {

						Thread.sleep(100);
						if (actived) {
							wait += 100;
						}

					}
				} catch (Exception e) {
				} finally {

					Intent splashScreen = new Intent(SplashScreenActivity.this,
							MainActivity.class);
					startActivity(splashScreen);
					overridePendingTransition(android.R.anim.fade_in,
							android.R.anim.fade_out);
					finish();
					onStop();
					onDestroy();

				}
			}
		}).start();
	}
}