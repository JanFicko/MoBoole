package xyz.janficko.moboole.util.JrawWrapper;

import android.content.Context;
import android.content.SharedPreferences;

import net.dean.jraw.auth.NoSuchTokenException;
import net.dean.jraw.auth.TokenStore;

import xyz.janficko.moboole.MoBoole;
import xyz.janficko.moboole.R;
import xyz.janficko.moboole.common.Keys;
import xyz.janficko.moboole.util.SharedPreferenceUtil;

/**
 * Simple implementation of TokenStore that uses SharedPreferences
 */
public class AndroidTokenStore implements TokenStore {

	private static final String TAG = AndroidTokenStore.class.getSimpleName();
	private SharedPreferenceUtil mSharedPreferenceUtil = MoBoole.getSharedPreferenceUtil();

	@Override
	public boolean isStored(String key) {
		return mSharedPreferenceUtil.hasKey(key);
	}

	@Override
	public String readToken(String key) throws NoSuchTokenException {
		String token = mSharedPreferenceUtil.retrieveString(key, null);
		if (token == null)
			throw new NoSuchTokenException("Token for key '" + key + "' does not exist");
		return token;
	}

	@Override
	public void writeToken(String key, String token) {
		mSharedPreferenceUtil.saveString(key, token);
	}

}
