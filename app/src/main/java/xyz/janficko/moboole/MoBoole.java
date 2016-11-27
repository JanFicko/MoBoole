package xyz.janficko.moboole;

import android.app.Application;

import net.dean.jraw.RedditClient;
import net.dean.jraw.auth.AuthenticationManager;
import net.dean.jraw.auth.RefreshTokenHandler;

import xyz.janficko.moboole.util.JrawWrapper.AndroidRedditClient;
import xyz.janficko.moboole.util.JrawWrapper.AndroidTokenStore;

public class MoBoole extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		RedditClient reddit = new AndroidRedditClient(this);
		RefreshTokenHandler handler = new RefreshTokenHandler(new AndroidTokenStore(this), reddit);
		AuthenticationManager.get().init(reddit, handler);
	}
}
