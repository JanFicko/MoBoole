package xyz.janficko.moboole.ui.onboarding;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xyz.janficko.moboole.R;
import xyz.janficko.moboole.model.OnBoardingPage;

public class OnBoardingPagerAdapter extends PagerAdapter {

    private Context context;

    OnBoardingPagerAdapter(Context context){
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        OnBoardingPage onBoardingPage = OnBoardingPage.values()[position];
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.page_onboarding, container, false);

        TextView title = layout.findViewById(R.id.onboarding_page_title);
        TextView body = layout.findViewById(R.id.onboarding_page_body);
        title.setText(onBoardingPage.getTitleResId());
        body.setText(onBoardingPage.getBodyResId());

        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return OnBoardingPage.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }



}
