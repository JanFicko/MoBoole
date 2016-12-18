package xyz.janficko.moboole.ui.submark;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class SubmarkFragment extends Fragment {

	private static final String TAG = SubmarkFragment.class.getSimpleName();
	private Activity activity;

	public SubmarkFragment() {
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		activity = (Activity) context;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}
