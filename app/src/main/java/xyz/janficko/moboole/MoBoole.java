package xyz.janficko.moboole;

import android.app.Application;

import net.dean.jraw.RedditClient;
import net.dean.jraw.auth.AuthenticationManager;
import net.dean.jraw.auth.RefreshTokenHandler;
import net.dean.jraw.http.oauth.Credentials;

import xyz.janficko.moboole.util.JrawWrapper.AndroidRedditClient;
import xyz.janficko.moboole.util.JrawWrapper.AndroidTokenStore;
import xyz.janficko.moboole.util.SharedPreferenceUtil;

public class MoBoole extends Application {

	private static final String TAG = MoBoole.class.getSimpleName();
    public static final Credentials CREDENTIALS = Credentials.installedApp(BuildConfig.CLIENT_ID, BuildConfig.REDIRECT);

	private static SharedPreferenceUtil mSharedPreferenceUtil;

	@Override
	public void onCreate() {
		super.onCreate();

        mSharedPreferenceUtil = new SharedPreferenceUtil(this);
        initRedditAuththentication();
	}

    /**
     * Instantiation of helper class to be used globally for easier handling of SharedPreference
     * value sets.
     *
     * @return Instance of SharedPreferenceUtil.
     */
    public static SharedPreferenceUtil getSharedPreferenceUtil() {
        return mSharedPreferenceUtil;
    }

    /**
     * Called at the start to create an instance a wrapper that is used to make Reddit API calls
     * and to refresh OAuth token.
     */
	private void initRedditAuththentication(){
		RedditClient reddit = new AndroidRedditClient(this);
		RefreshTokenHandler handler = new RefreshTokenHandler(new AndroidTokenStore(), reddit);
		AuthenticationManager.get().init(reddit, handler);
	}
}
