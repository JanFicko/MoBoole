package xyz.janficko.moboole;

import android.app.Application;

import net.dean.jraw.RedditClient;
import net.dean.jraw.auth.AuthenticationManager;
import net.dean.jraw.auth.RefreshTokenHandler;
import net.dean.jraw.http.oauth.Credentials;

import xyz.janficko.moboole.util.JrawWrapper.AndroidRedditClient;
import xyz.janficko.moboole.util.JrawWrapper.AndroidTokenStore;

public class MoBoole extends Application {

	private static final String TAG = MoBoole.class.getSimpleName();
	public static final Credentials CREDENTIALS = Credentials.installedApp(BuildConfig.CLIENT_ID, BuildConfig.REDIRECT);

	@Override
	public void onCreate() {
		super.onCreate();
		RedditClient reddit = new AndroidRedditClient(this);
		RefreshTokenHandler handler = new RefreshTokenHandler(new AndroidTokenStore(this), reddit);
		AuthenticationManager.get().init(reddit, handler);
	}
}
