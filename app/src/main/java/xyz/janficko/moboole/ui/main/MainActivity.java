package xyz.janficko.moboole.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;


import butterknife.BindView;
import xyz.janficko.moboole.R;
import xyz.janficko.moboole.ui.BaseActivity;
import xyz.janficko.moboole.ui.profile.ProfileFragment;
import xyz.janficko.moboole.ui.frontpage.FrontpageFragment;
import xyz.janficko.moboole.ui.search.SearchFragment;
import xyz.janficko.moboole.ui.submark.SubmarkFragment;
import xyz.janficko.moboole.util.NetworkUtil;
import xyz.janficko.moboole.util.SnackbarUtil;

public class MainActivity extends BaseActivity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private FragmentTransaction fragmentTransaction;
	private FrontpageFragment frontpageFragment;
	private SubmarkFragment submarkFragment;
	private SearchFragment searchFragment;
	private ProfileFragment profileFragment;
	private int currentTab;

	@BindView(R.id.coordinatorLayout)
	CoordinatorLayout coordinatorLayout;
	@BindView(R.id.bottom_bar)
	BottomBar bottomBar;
	@BindView(R.id.toolbar)
	Toolbar toolbar;

	public static void start(Context context) {
	    Intent starter = new Intent(context, MainActivity.class);
	    context.startActivity(starter);
	}

	@Override
	protected int setLayoutResId() {
		return R.layout.activity_main;
	}

	@Override
	protected void onResume() {
		super.onResume();

		setupToolbar();
		setupBottomBar();

		if (!NetworkUtil.isNetworkAvailable(this)) {
			SnackbarUtil.snackbarNoInternet(this, coordinatorLayout);
            Log.d(TAG, "false");
		}
		/*AuthenticationState state = AuthenticationManager.get().checkAuthState();
		Logger.print(TAG, "AuthenticationState for onResume(): " + state);

		switch (state) {
			case READY:
				break;
			case NONE:
				Toast.makeText(MainActivity.this, "Log in first", Toast.LENGTH_SHORT).show();
				break;
			case NEED_REFRESH:
				refreshAccessTokenAsync();
				break;
			default:
				Logger.printError(TAG, "State doesn't exist.");
		}*/
	}

	private void setupToolbar() {
		toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
		setSupportActionBar(toolbar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_sort_subreddit:
                Log.d(TAG, "Sort subreddit.");
				break;
			case R.id.action_search_subreddit:
                Log.d(TAG, "Search subreddit.");
				break;
			default:
                Log.e(TAG, "Selected menu doesn't exist.");
		}
		return false;
	}

	private void setupBottomBar() {
		bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
			@Override
			public void onTabSelected(@IdRes int tabId) {
				fragmentTransaction = getSupportFragmentManager().beginTransaction();
				AppBarLayout.LayoutParams toolbarLayout = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
				switch (tabId) {
					case R.id.tab_frontpage:
                        Log.d(TAG, "Frontpage.");
						toolbarLayout.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);

						currentTab = R.id.tab_frontpage;
						frontpageFragment = new FrontpageFragment();
						fragmentTransaction.replace(R.id.content_container, frontpageFragment);
						fragmentTransaction.commit();
						break;
					case R.id.tab_submark:
                        Log.d(TAG, "Submark.");
						toolbarLayout.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
						currentTab = R.id.tab_submark;
						submarkFragment = new SubmarkFragment();
						fragmentTransaction.replace(R.id.content_container, submarkFragment);
						fragmentTransaction.commit();
						break;
					case R.id.tab_search:
                        Log.d(TAG, "Search.");
						toolbarLayout.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
						currentTab = R.id.tab_search;
						searchFragment = new SearchFragment();
						fragmentTransaction.replace(R.id.content_container, searchFragment);
						fragmentTransaction.commit();
						break;
					case R.id.tab_profile:
                        Log.d(TAG, "Profile.");
						toolbarLayout.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
						currentTab = R.id.tab_profile;
						profileFragment = new ProfileFragment();
						fragmentTransaction.replace(R.id.content_container, profileFragment);
						fragmentTransaction.commit();
						break;
					default:
                        Log.e(TAG, "Selected tab doesn't exist.");
				}
			}
		});
	}

	/*public void login(View view) {
		startActivity(new Intent(this, LoginActivity.class));
	}

	public void userInfo(View view) {
		startActivity(new Intent(this, UserInfoActivity.class));
	}*/

	private void refreshAccessTokenAsync() {
		/*new AsyncTask<Credentials, Void, Void>() {
			@Override
			protected Void doInBackground(Credentials... params) {
				try {
					AuthenticationManager.get().refreshAccessToken(MoBoole.CREDENTIALS);
				} catch (NoSuchTokenException | OAuthException e) {
					Logger.printError(TAG, "Could not refresh access token" + e);
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void v) {
				Logger.print(TAG, "Reauthenticated");
			}
		}.execute();*/
	}
}
