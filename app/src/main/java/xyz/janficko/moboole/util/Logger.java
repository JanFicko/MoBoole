package xyz.janficko.moboole.util;


import android.util.Log;

import xyz.janficko.moboole.BuildConfig;

public class Logger {

	private static boolean PRINT_LOG = BuildConfig.DEBUG;

	public static void setPrintLog(boolean printLog) {
		PRINT_LOG = printLog;
	}

	public static void print(String TAG, String... messages) {
		if (PRINT_LOG) {
			for (String message : messages) {
				if (message != null) {
					Log.v(TAG, message);
				}
			}
		}
	}

	public static void printError(String TAG, String... messages) {
		if (PRINT_LOG) {
			for (String message : messages) {
				if (message != null) {
					Log.e(TAG, message);
				}
			}
		}
	}

}
