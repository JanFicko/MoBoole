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

import net.dean.jraw.auth.AuthenticationManager;
import net.dean.jraw.models.LoggedInAccount;

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

		/*FetchUserInfoTask fetchUserInfoTask = new FetchUserInfoTask();
		fetchUserInfoTask.execute(view);*/

		return view;
	}

	private static class FetchUserInfoTask extends AsyncTask<View, Void, LoggedInAccount> {
		private View view;

		@Override
		protected LoggedInAccount doInBackground(View... view) {
			this.view = view[0];
			return AuthenticationManager.get().getRedditClient().me();
		}

		@Override
		protected void onPostExecute(LoggedInAccount data) {
			((TextView) view.findViewById(R.id.user_name)).setText("Name: " + data.getFullName());
			((TextView) view.findViewById(R.id.user_created)).setText("Created: " + data.getCreated());
			((TextView) view.findViewById(R.id.user_link_karma)).setText("Link karma: " + data.getLinkKarma());
			((TextView) view.findViewById(R.id.user_comment_karma)).setText("Comment karma: " + data.getCommentKarma());
			((TextView) view.findViewById(R.id.user_has_mail)).setText("Has mail? " + (data.getInboxCount() > 0));
			((TextView) view.findViewById(R.id.user_inbox_count)).setText("Inbox count: " + data.getInboxCount());
			((TextView) view.findViewById(R.id.user_is_mod)).setText("Is mod? " + data.isMod());
		}
	}





}