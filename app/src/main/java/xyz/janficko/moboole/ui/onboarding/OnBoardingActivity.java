package xyz.janficko.moboole.ui.onboarding;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.janficko.moboole.R;
import xyz.janficko.moboole.ui.BaseActivity;
import xyz.janficko.moboole.ui.main.MainActivity;

public class OnBoardingActivity extends BaseActivity implements
        OnBoardingContract.View {

    @BindView(R.id.onboarding_view_pager)
    ViewPager viewPager;
    @BindView(R.id.onboarding_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.onboarding_button)
    AppCompatButton mButton;

    public static void start(Context context) {
        Intent starter = new Intent(context, OnBoardingActivity.class);
        context.startActivity(starter);
    }

	@Override
	protected int setLayoutResId() {
		return R.layout.activity_onboarding;
	}

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.setAdapter(new OnBoardingPagerAdapter(this));
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @OnClick(R.id.onboarding_button)
    @Override
    public void onClickStart() {
        MainActivity.start(this);
        finishAffinity();
    }

}
