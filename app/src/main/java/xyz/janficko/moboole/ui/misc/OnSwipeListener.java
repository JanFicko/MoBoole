package xyz.janficko.moboole.ui.misc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.LinearLayout;

import xyz.janficko.moboole.util.Logger;

public class OnSwipeListener extends ItemTouchHelper.SimpleCallback {

	private static final String TAG = OnSwipeListener.class.getSimpleName();

	public OnSwipeListener(int dragDirs, int swipeDirs) {
		super(dragDirs, swipeDirs);
	}

	@Override
	public void onChildDraw(Canvas c, RecyclerView recyclerView,
							RecyclerView.ViewHolder viewHolder, float dX, float dY,
							int actionState, boolean isCurrentlyActive) {

		if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
			/*// Get RecyclerView item from the ViewHolder
			View itemView = viewHolder.itemView;

			Paint p = new Paint();
			if (dX > 0) {
            *//* Set your color for positive displacement *//*

				// Draw Rect with varying right side, equal to displacement dX
				c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
						(float) itemView.getBottom(), p);
			} else {
            *//* Set your color for negative displacement *//*

				// Draw Rect with varying left side, equal to the item's right side plus negative displacement dX
				c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
						(float) itemView.getRight(), (float) itemView.getBottom(), p);
			}*/

			super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
		}
	}

	@Override
	public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
		return false;
	}

	@Override
	public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
		if (direction == ItemTouchHelper.RIGHT) {
			Logger.print(TAG, "DESNO - UPVOTE");
		} else if (direction == ItemTouchHelper.LEFT) {
			Logger.print(TAG, "LEFT - DOWNVOTE");
		}
	}
}
