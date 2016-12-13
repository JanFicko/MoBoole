package xyz.janficko.moboole.ui.home;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.janficko.moboole.R;
import xyz.janficko.moboole.ui.frontpage.FrontpageFragment;
import xyz.janficko.moboole.util.Logger;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private FragmentTransaction fragmentTransaction;
	private FrontpageFragment frontpageFragment;

	@BindView(R.id.bottom_bar)
	BottomBar bottomBar;

	ArrayAdapter<String> mForecastAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setupToolbar();

		ButterKnife.bind(this);
		bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
			@Override
			public void onTabSelected(@IdRes int tabId) {
				fragmentTransaction = getSupportFragmentManager().beginTransaction();
				switch (tabId) {
					case R.id.tab_frontpage:
						Logger.print(TAG, "Frontpage.");
						frontpageFragment = new FrontpageFragment();
						fragmentTransaction.replace(R.id.content_container, frontpageFragment);
						fragmentTransaction.commit();
						break;
					case R.id.tab_submark:
						Logger.print(TAG, "Submark.");
						/*SubmarkFragment submarkFragment = new SubmarkFragment();
						getSupportFragmentManager()
								.beginTransaction()
								.replace(R.id.contentContainer, submarkFragment, submarkFragment.getClass().getSimpleName())
								.addToBackStack(null).commit();*/
						break;
					case R.id.tab_search:
						Logger.print(TAG, "Search.");
						break;
					case R.id.tab_profile:
						Logger.print(TAG, "Profile.");
						break;
					default:
						Logger.print(TAG, "Selected tab doesn't exists.");
				}
			}
		});
	}

	private void setupToolbar() {
		/*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		toolbar.setPadding(0, getStatusBarHeight(), 0, 0);*/
	}

	public int getStatusBarHeight() {
		int height = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			height = getResources().getDimensionPixelOffset(resourceId);
		}
		return height;
	}

	/*public void login(View view) {
		startActivity(new Intent(this, LoginActivity.class));
	}

	public void userInfo(View view) {
		startActivity(new Intent(this, UserInfoActivity.class));
	}

	@Override
	protected void onResume() {
		super.onResume();
		AuthenticationState state = AuthenticationManager.get().checkAuthState();
		Log.d(TAG, "AuthenticationState for onResume(): " + state);

		switch (state) {
			case READY:
				break;
			case NONE:
				Toast.makeText(MainActivity.this, "Log in first", Toast.LENGTH_SHORT).show();
				break;
			case NEED_REFRESH:
				refreshAccessTokenAsync();
				break;
		}
	}

	private void refreshAccessTokenAsync() {
		new AsyncTask<Credentials, Void, Void>() {
			@Override
			protected Void doInBackground(Credentials... params) {
				try {
					AuthenticationManager.get().refreshAccessToken(LoginActivity.CREDENTIALS);
				} catch (NoSuchTokenException | OAuthException e) {
					Log.e(TAG, "Could not refresh access token", e);
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void v) {
				Log.d(TAG, "Reauthenticated");
			}
		}.execute();
	}*/
}
