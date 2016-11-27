package xyz.janficko.moboole.ui.intro;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import xyz.janficko.moboole.R;
import xyz.janficko.moboole.ui.home.MainActivity;

public class IntroActivity extends AppIntro2 {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		//addSlide(firstFragment);
		//addSlide(secondFragment);
		//addSlide(thirdFragment);
		//addSlide(fourthFragment);

		addSlide(AppIntroFragment.newInstance("Welcome to MoBoole!", "This app was created with the purpose of comparing development for Android and Android TV platforms.", R.mipmap.ic_launcher, Color.parseColor("#1976D2")));
		addSlide(AppIntroFragment.newInstance("Permission Request", "In order to store images in cache, you must give permissions.", R.mipmap.ic_launcher, Color.parseColor("#1976D2")));
		addSlide(AppIntroFragment.newInstance("Simple, yet Customizable", "The library offers a lot of customization, while keeping it simple for those that like simple.", R.mipmap.ic_launcher, Color.parseColor("#1976D2")));
		addSlide(AppIntroFragment.newInstance("Explore", "Feel free to explore the rest of the library demo!", R.mipmap.ic_launcher, Color.parseColor("#1976D2")));

		// Here we load a string array with a camera permission, and tell the library to request permissions on slide 2
		//askForPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS}, 2);

		setFlowAnimation();
		showStatusBar(false);
		showSkipButton(false);

	}

	@Override
	public void onDonePressed(Fragment currentFragment) {
		super.onDonePressed(currentFragment);
		finish();

		Intent i = new Intent(IntroActivity.this, MainActivity.class);
		startActivity(i);
	}
}
