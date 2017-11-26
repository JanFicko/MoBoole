package xyz.janficko.moboole.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import xyz.janficko.moboole.MoBoole;
import xyz.janficko.moboole.common.Keys;
import xyz.janficko.moboole.ui.main.MainActivity;
import xyz.janficko.moboole.ui.onboarding.OnBoardingActivity;
import xyz.janficko.moboole.util.SharedPreferenceUtil;

public class SplashActivity extends AppCompatActivity {

	private static final String TAG = SplashActivity.class.getSimpleName();
	private SharedPreferenceUtil mSharedPreferenceUtil = MoBoole.getSharedPreferenceUtil();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		boolean isFirstStart = mSharedPreferenceUtil.retrieveBoolean(Keys.PREF_IS_FIRST_START, true);
		if (isFirstStart) {
			Intent i = new Intent(SplashActivity.this, OnBoardingActivity.class);
			startActivity(i);

            //mSharedPreferenceUtil.saveBoolean(Keys.PREF_IS_FIRST_START, false);
		} else {
			Intent i = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(i);
        }
		finishAffinity();
	}
}
