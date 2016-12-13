package xyz.janficko.moboole.ui.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import xyz.janficko.moboole.ui.home.MainActivity;
import xyz.janficko.moboole.ui.intro.IntroActivity;
import xyz.janficko.moboole.util.Logger;

public class SplashActivity extends AppCompatActivity {

	private static final String TAG = SplashActivity.class.getSimpleName();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		boolean isFirstStart = getPrefs.getBoolean("firstStart", true);
		if (isFirstStart) {
			Intent i = new Intent(SplashActivity.this, IntroActivity.class);
			startActivity(i);
			SharedPreferences.Editor e = getPrefs.edit();
			e.putBoolean("firstStart", false);
			e.apply();
		} else {
			Intent i = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(i);
			}
		finish();
	}
}
