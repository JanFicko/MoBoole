package xyz.janficko.moboole.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import xyz.janficko.moboole.MoBoole;
import xyz.janficko.moboole.common.Constants;
import xyz.janficko.moboole.common.Keys;
import xyz.janficko.moboole.ui.BaseActivity;
import xyz.janficko.moboole.ui.main.MainActivity;
import xyz.janficko.moboole.ui.onboarding.OnBoardingActivity;
import xyz.janficko.moboole.util.SharedPreferenceUtil;

public class SplashActivity extends BaseActivity {

	private static final String TAG = SplashActivity.class.getSimpleName();
	private SharedPreferenceUtil mSharedPreferenceUtil = MoBoole.getSharedPreferenceUtil();

    @Override
    protected int setLayoutResId() {
        return Constants.NO_LAYOUT;
    }

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		boolean isFirstStart = mSharedPreferenceUtil.retrieveBoolean(Keys.PREF_IS_FIRST_START, true);
		if (isFirstStart) {
			OnBoardingActivity.start(this);
            //mSharedPreferenceUtil.saveBoolean(Keys.PREF_IS_FIRST_START, false);
		} else {
		    MainActivity.start(this);
        }
		finishAffinity();
	}

}
