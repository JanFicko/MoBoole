package xyz.janficko.moboole.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import xyz.janficko.moboole.R;

public class SnackbarFactory {

	public static void snackbarMessage(Context context, View view, String message) {
		Snackbar snackbar = Snackbar
				.make(view, message, Snackbar.LENGTH_LONG);
		snackbar.show();
	}

	public static void snackbarNoInternet(Context context, View view) {
		Snackbar snackbar = Snackbar
				.make(view, R.string.error_no_internet, Snackbar.LENGTH_INDEFINITE)
				.setAction("RETRY", new View.OnClickListener() {
					@Override
					public void onClick(View view) {
					}
				});
		snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.colorAccent));
		snackbar.show();
	}

}
