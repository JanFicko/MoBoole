package xyz.janficko.moboole.ui.frontpage;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import xyz.janficko.moboole.R;
import xyz.janficko.moboole.model.MockModel;
import xyz.janficko.moboole.ui.misc.OnSwipeListener;
import xyz.janficko.moboole.util.Logger;

public class FrontpageFragment extends Fragment {

	private static final String TAG = FrontpageFragment.class.getSimpleName();
	private Activity activity;
	private RecyclerView.Adapter recyclerAdapter;
	private RecyclerView.LayoutManager linearLayoutManager;
	private RecyclerView recyclerView;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		activity = (Activity) context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.recycler_view, container, false);

		if (view instanceof RecyclerView) {
			recyclerView = (RecyclerView) view;
			recyclerView.setHasFixedSize(true);
			linearLayoutManager = new LinearLayoutManager(activity);
			linearLayoutManager.setAutoMeasureEnabled(true);
			recyclerView.setLayoutManager(linearLayoutManager);
			recyclerAdapter = new FrontpageRecyclerViewAdapter(mockDataSet());
			recyclerView.setAdapter(recyclerAdapter);

			OnSwipeListener onSwipeListener = new OnSwipeListener(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
			ItemTouchHelper itemTouchHelper = new ItemTouchHelper(onSwipeListener);
			itemTouchHelper.attachToRecyclerView(recyclerView);
		}

		return view;
	}

	// TODO: Replace with real data and remove this.
	private ArrayList<MockModel> mockDataSet() {
		ArrayList<MockModel> mockDataList = new ArrayList<>();

		for (int i = 0; i < 15; i++) {
			mockDataList.add(new MockModel(i, "Image" + i, "Title" + i, "Content" + i, "Author" + i, i, i, "Subreddit" + i, "Source" + i, i + "h", "Flair" + i));
		}

		return mockDataList;
	}

}
