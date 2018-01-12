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
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

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

		// OAuth2 scopes to request. See https://www.reddit.com/dev/api/oauth for a full list
		String[] scopes = {"identity", "edit", "flair", "history", "modconfig", "modflair",
				"modlog", "modposts", "modwiki", "mysubreddits", "privatemessages", "read",
				"report", "save", "submit", "subscribe", "vote", "wikiedit", "wikiread"};
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
