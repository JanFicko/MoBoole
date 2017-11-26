package xyz.janficko.moboole.ui.onboarding;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.janficko.moboole.R;
import xyz.janficko.moboole.model.OnBoardingPage;
import xyz.janficko.moboole.ui.base.BaseActivity;
import xyz.janficko.moboole.ui.main.MainActivity;
import xyz.janficko.moboole.ui.splash.SplashActivity;

public class OnBoardingActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.onboarding_view_pager)
    ViewPager mViewPager;
    @BindView(R.id.onboarding_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.onboarding_button)
    AppCompatButton mButton;

	@Override
	protected int getLayoutResId() {
		return R.layout.activity_onboarding;
	}

    @Override
    protected void onResume() {
        super.onResume();
        PagerAdapter pagerAdapter = new OnBoardingPagerAdapter(this);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(this);

        mTabLayout.setupWithViewPager(mViewPager, true);
    }

    /**
     * Callback when page on ViewPager is changed. Button text is changed on last page to let
     * the user know that he is done with the OnBoarding view.
     *
     * @param position of current shown page.
     */
    @Override
    public void onPageSelected(int position) {
        if((OnBoardingPage.values().length - 1) == position){
            mButton.setText(R.string.done);
        } else {
            mButton.setText(R.string.skip);
        }
    }

    @OnClick(R.id.onboarding_button)
    public void onClickButton(){
        Intent i = new Intent(OnBoardingActivity.this, MainActivity.class);
        startActivity(i);
        finishAffinity();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
