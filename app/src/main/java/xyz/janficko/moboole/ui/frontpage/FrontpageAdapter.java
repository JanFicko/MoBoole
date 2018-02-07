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


public class FrontpageAdapter extends RecyclerView.Adapter<FrontpageAdapter.ItemViewHolder> {

	private static final String TAG = FrontpageAdapter.class.getSimpleName();

	private List<MockModel> mockModelList;

	public FrontpageAdapter(List<MockModel> mockModelList) {
		this.mockModelList = mockModelList;
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.card_view, parent, false);
		return new ItemViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ItemViewHolder holder, int position) {
		holder.bind(position);
	}

	@Override
	public int getItemCount() {
		if (mockModelList != null) {
            return mockModelList.size();
		} else {
		    return 0;
        }
	}

	class ItemViewHolder extends RecyclerView.ViewHolder {

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


		ItemViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

        public void bind(int position) {
            MockModel mockModel = mockModelList.get(position);
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
