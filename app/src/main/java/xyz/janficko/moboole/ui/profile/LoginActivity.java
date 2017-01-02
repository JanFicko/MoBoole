package xyz.janficko.moboole.ui.profile;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import net.dean.jraw.auth.AuthenticationManager;
import net.dean.jraw.http.NetworkException;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.http.oauth.OAuthHelper;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.janficko.moboole.MoBoole;
import xyz.janficko.moboole.R;

public class LoginActivity extends AppCompatActivity {

	private static final String TAG = LoginActivity.class.getSimpleName();

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.web_view)
	WebView webView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);

		setupToolbar();

		// Create our RedditClient
		final OAuthHelper helper = AuthenticationManager.get().getRedditClient().getOAuthHelper();

		// OAuth2 scopes to request. See https://www.reddit.com/dev/api/oauth for a full list
		String[] scopes = {"identity", "edit", "flair", "history", "modconfig", "modflair",
				"modlog", "modposts", "modwiki", "mysubreddits", "privatemessages", "read",
				"report", "save", "submit", "subscribe", "vote", "wikiedit", "wikiread"};

		final URL authorizationUrl = helper.getAuthorizationUrl(MoBoole.CREDENTIALS, true, true, scopes);

		// Load the authorization URL into the browser
		webView.loadUrl(authorizationUrl.toExternalForm());
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				if (url.contains("code=")) {
					// We've detected the redirect URL
					onUserChallenge(url, MoBoole.CREDENTIALS);
				} else if (url.contains("error=")) {
					Toast.makeText(LoginActivity.this, "You must press 'allow' to log in with this account", Toast.LENGTH_SHORT).show();
					webView.loadUrl(authorizationUrl.toExternalForm());
				}
			}
		});
	}

	private void onUserChallenge(final String url, final Credentials creds) {
		new AsyncTask<String, Void, String>() {
			@Override
			protected String doInBackground(String... params) {
				try {
					OAuthData data = AuthenticationManager.get().getRedditClient().getOAuthHelper().onUserChallenge(params[0], creds);
					AuthenticationManager.get().getRedditClient().authenticate(data);
					return AuthenticationManager.get().getRedditClient().getAuthenticatedUser();
				} catch (NetworkException | OAuthException e) {
					Log.e("LoginActivity", "Could not log in", e);
					return null;
				}
			}

			@Override
			protected void onPostExecute(String s) {
				Log.i("LoginActivity", s);
				LoginActivity.this.finish();
			}
		}.execute(url);
	}

	private void setupToolbar() {
		toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setDisplayShowHomeEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			LoginActivity.this.finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
