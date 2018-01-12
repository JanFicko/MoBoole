package xyz.janficko.moboole.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.janficko.moboole.R;

public class ProfileFragment extends Fragment {

	private static final String TAG = ProfileFragment.class.getSimpleName();
	private Activity activity;

	@BindView(R.id.login_button)
	Button loginButton;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		activity = (Activity) context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_profile, container, false);
		ButterKnife.bind(this, view);

		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, LoginActivity.class);
				startActivity(intent);
			}
		});

		return view;
	}





}