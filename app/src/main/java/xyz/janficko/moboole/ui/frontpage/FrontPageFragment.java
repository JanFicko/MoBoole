package xyz.janficko.moboole.ui.frontpage;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import xyz.janficko.moboole.R;
import xyz.janficko.moboole.model.MockModel;
import xyz.janficko.moboole.ui.BaseFragment;
import xyz.janficko.moboole.ui.main.MainActivity;

public class FrontPageFragment extends BaseFragment implements FrontPageContract.View {

    private FrontPageContract.Presenter presenter;

    @BindView(R.id.rv_frontpage)
	RecyclerView rvFrontPage;

	public static FrontPageFragment newInstance() {
		Bundle args = new Bundle();
		FrontPageFragment fragment = new FrontPageFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected int setLayoutResId() {
		return R.layout.fragment_frontpage;
	}

    @Override
    public void setPresenter(FrontPageContract.Presenter presenter) {
        this.presenter = presenter;
    }

	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.recycler_view, container, false);

		if (view instanceof RecyclerView) {
			recyclerView = (RecyclerView) view;
			recyclerView.setHasFixedSize(true);
			linearLayoutManager = new LinearLayoutManager(context);
			linearLayoutManager.setAutoMeasureEnabled(true);
			recyclerView.setLayoutManager(linearLayoutManager);
			recyclerAdapter = new FrontpageAdapter(mockDataSet());
			recyclerView.setAdapter(recyclerAdapter);

			OnSwipeListener onSwipeListener = new OnSwipeListener(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
			ItemTouchHelper itemTouchHelper = new ItemTouchHelper(onSwipeListener);
			itemTouchHelper.attachToRecyclerView(recyclerView);
		}

		return view;
	}*/

    @Override
    public void onResume() {
        super.onResume();

        rvFrontPage.setHasFixedSize(true);
        rvFrontPage.setLayoutManager(new LinearLayoutManager(context));
        FrontpageAdapter frontpageAdapter = new FrontpageAdapter(mockDataSet());
        rvFrontPage.setAdapter(frontpageAdapter);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            rvFrontPage.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy < 0) {
                        ((MainActivity) baseActivity).hideBottomBar(false);
                    } else {
                        ((MainActivity) baseActivity).hideBottomBar(true);
                    }
                }
            });
        }
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
