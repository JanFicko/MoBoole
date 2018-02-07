package xyz.janficko.moboole.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.janficko.moboole.R;
import xyz.janficko.moboole.ui.BaseActivity;
import xyz.janficko.moboole.ui.frontpage.FrontPagePresenter;
import xyz.janficko.moboole.ui.profile.ProfileFragment;
import xyz.janficko.moboole.ui.frontpage.FrontPageFragment;
import xyz.janficko.moboole.ui.search.SearchFragment;
import xyz.janficko.moboole.ui.submark.SubmarkFragment;
import xyz.janficko.moboole.util.UnitUtil;

public class MainActivity extends BaseActivity implements MainContract.View {

    private static final String SELECTED_BOTTOM_BAR_ITEM = "SELECTED_BOTTOM_BAR_ITEM";

	private FrontPageFragment frontPageFragment;

	@BindView(R.id.v_main_bottom_bar_divider)
    View vBottomBarDivider;
	@BindView(R.id.bb_main)
    ConstraintLayout clBottomBar;
	@BindView(R.id.iv_frontpage)
    ImageView ivFrontpage;
	@BindView(R.id.iv_submark)
    ImageView ivSubmark;
	@BindView(R.id.iv_search)
    ImageView ivSearch;
	@BindView(R.id.iv_profile)
    ImageView ivProfile;

	public static void start(Context context) {
	    Intent starter = new Intent(context, MainActivity.class);
	    context.startActivity(starter);
	}

	@Override
	protected int setLayoutResId() {
		return R.layout.activity_main;
	}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            ivFrontpage.setSelected(true);
            onBottomBarItemClicked(findViewById(R.id.iv_frontpage));
            switchFragments(null, null);
        } else {
            /* Restore instance of selected bottom bar item. */
            onBottomBarItemClicked(findViewById(savedInstanceState.getInt(SELECTED_BOTTOM_BAR_ITEM)));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(ivFrontpage.isSelected()){
            outState.putInt(SELECTED_BOTTOM_BAR_ITEM, ivFrontpage.getId());
        } else if(ivSubmark.isSelected()){
            outState.putInt(SELECTED_BOTTOM_BAR_ITEM, ivSubmark.getId());
        } else if(ivSearch.isSelected()){
            outState.putInt(SELECTED_BOTTOM_BAR_ITEM, ivSearch.getId());
        } else if(ivProfile.isSelected()){
            outState.putInt(SELECTED_BOTTOM_BAR_ITEM, ivProfile.getId());
        }
    }

    @Override
    public void switchFragments(Fragment fromFragment, Fragment toFragment) {
        if(fromFragment == null || fromFragment instanceof FrontPageFragment){
            if(toFragment == null){
                frontPageFragment = FrontPageFragment.newInstance();
                new FrontPagePresenter(frontPageFragment);
                moveToNextFragment(frontPageFragment);
            }
        }

    }

    @OnClick({ R.id.iv_frontpage, R.id.iv_submark, R.id.iv_search, R.id.iv_profile })
    @Override
    public void onBottomBarItemClicked(View view) {
	    ivFrontpage.setSelected(view.getId() == R.id.iv_frontpage);
        ivSubmark.setSelected(view.getId() == R.id.iv_submark);
        ivSearch.setSelected(view.getId() == R.id.iv_search);
        ivProfile.setSelected(view.getId() == R.id.iv_profile);
    }

    @Override
    public void hideBottomBar(boolean isHidden) {
        if(isHidden){
            //clBottomBar.setVisibility(View.GONE);
            vBottomBarDivider.animate().translationY(UnitUtil.convertDpToPixel(40, this));
            clBottomBar.animate().translationY(UnitUtil.convertDpToPixel(40, this));
        } else {
            //clBottomBar.setVisibility(View.VISIBLE);
            vBottomBarDivider.animate().translationY(0);
            clBottomBar.animate().translationY(0);
        }
    }
}
