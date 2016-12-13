package xyz.janficko.moboole.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import xyz.janficko.moboole.R;

public class SnackbarFactory {

	public static Snackbar createSnackbar(Context context, View view, String message) {
		Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
		ViewGroup group = (ViewGroup) snackbar.getView();
		group.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
		return snackbar;
	}

}
