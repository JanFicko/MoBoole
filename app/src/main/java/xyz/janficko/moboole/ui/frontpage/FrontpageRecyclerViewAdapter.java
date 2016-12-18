package xyz.janficko.moboole.ui.frontpage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.janficko.moboole.R;
import xyz.janficko.moboole.model.MockModel;
import xyz.janficko.moboole.util.Logger;


public class FrontpageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private static final String TAG = FrontpageRecyclerViewAdapter.class.getSimpleName();

	private List<MockModel> mockModelList;
	private int expandedItemPosition = -1;

	public FrontpageRecyclerViewAdapter(List<MockModel> mockModelList) {
		this.mockModelList = mockModelList;
		Logger.print(TAG, "object");
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.card_view, parent, false);
		Logger.print(TAG, "onCreateView");
		return new ItemViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		MockModel m = mockModelList.get(position);
		((ItemViewHolder) holder).bind(m);
		Logger.print(TAG, "onBindViewHolder");
	}


	@Override
	public int getItemCount() {
		Logger.print(TAG, "itemCount");
		int size = mockModelList.size();
		if (expandedItemPosition >= 0) {
			//size += mockModelList.get(expandedItemPosition)
		}
		return size;
	}

	public static class ItemViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.text_subreddit)
		TextView subreddit;
		@BindView(R.id.text_source)
		TextView source;
		@BindView(R.id.text_submitted)
		TextView submitted;
		@BindView(R.id.text_user)
		TextView user;
		@BindView(R.id.text_link_flair)
		TextView flair;
		@BindView(R.id.text_title)
		TextView title;
		@BindView(R.id.text_comments)
		TextView comments;
		@BindView(R.id.image_comments)
		ImageView iconComments;
		@BindView(R.id.image_upvote)
		ImageView iconUpvote;
		@BindView(R.id.text_votes)
		TextView votes;
		@BindView(R.id.image_downvote)
		ImageView iconDownvote;


		public ItemViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		public void bind(MockModel mockModel) {
			subreddit.setText(mockModel.getSubreddit());
			source.setText(mockModel.getSource());
			submitted.setText(mockModel.getSubmitted());
			user.setText(mockModel.getUser());
			flair.setText(mockModel.getLinkFlair());
			title.setText(mockModel.getTitle());
			comments.setText(String.valueOf(mockModel.getComments()));
			votes.setText(String.valueOf(mockModel.getVotes()));

		}
	}

}
