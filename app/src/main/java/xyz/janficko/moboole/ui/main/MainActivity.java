package xyz.janficko.moboole.ui.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import net.dean.jraw.auth.AuthenticationManager;
import net.dean.jraw.auth.AuthenticationState;
import net.dean.jraw.auth.NoSuchTokenException;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthException;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.janficko.moboole.MoBoole;
import xyz.janficko.moboole.R;
import xyz.janficko.moboole.ui.profile.ProfileFragment;
import xyz.janficko.moboole.ui.frontpage.FrontpageFragment;
import xyz.janficko.moboole.ui.search.SearchFragment;
import xyz.janficko.moboole.ui.submark.SubmarkFragment;
import xyz.janficko.moboole.util.Logger;
import xyz.janficko.moboole.util.NetworkUtil;
import xyz.janficko.moboole.util.SnackbarFactory;

public class MainActivity extends AppCompatActivity {

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		setupToolbar();
		setupBottomBar();

		if (!NetworkUtil.isNetworkAvailable(this)) {
			SnackbarFactory.snackbarNoInternet(this, coordinatorLayout);
			Logger.print(TAG, "false");
		}
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
				Logger.print(TAG, "Sort subreddit.");
				break;
			case R.id.action_search_subreddit:
				Logger.print(TAG, "Search subreddit.");
				break;
			default:
				Logger.printError(TAG, "Selected menu doesn't exist.");
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
						Logger.print(TAG, "Frontpage.");
						toolbarLayout.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);

						currentTab = R.id.tab_frontpage;
						frontpageFragment = new FrontpageFragment();
						fragmentTransaction.replace(R.id.content_container, frontpageFragment);
						fragmentTransaction.commit();
						break;
					case R.id.tab_submark:
						Logger.print(TAG, "Submark.");
						toolbarLayout.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
						currentTab = R.id.tab_submark;
						submarkFragment = new SubmarkFragment();
						fragmentTransaction.replace(R.id.content_container, submarkFragment);
						fragmentTransaction.commit();
						break;
					case R.id.tab_search:
						Logger.print(TAG, "Search.");
						toolbarLayout.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
						currentTab = R.id.tab_search;
						searchFragment = new SearchFragment();
						fragmentTransaction.replace(R.id.content_container, searchFragment);
						fragmentTransaction.commit();
						break;
					case R.id.tab_profile:
						Logger.print(TAG, "Profile.");
						toolbarLayout.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
						currentTab = R.id.tab_profile;
						profileFragment = new ProfileFragment();
						fragmentTransaction.replace(R.id.content_container, profileFragment);
						fragmentTransaction.commit();
						break;
					default:
						Logger.printError(TAG, "Selected tab doesn't exist.");
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

	@Override
	protected void onResume() {
		super.onResume();
		AuthenticationState state = AuthenticationManager.get().checkAuthState();
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
		}
	}


	private void refreshAccessTokenAsync() {
		new AsyncTask<Credentials, Void, Void>() {
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
		}.execute();
	}
}
