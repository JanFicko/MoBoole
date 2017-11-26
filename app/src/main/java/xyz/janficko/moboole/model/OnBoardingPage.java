package xyz.janficko.moboole.model;

import xyz.janficko.moboole.R;

public enum OnBoardingPage {

    RED(R.string.title_intro1, R.string.description_intro1, 0),
    BLUE(R.string.title_intro2, R.string.description_intro2, 0),
    GREEN(R.string.title_intro3, R.string.description_intro3, 0);

    private int mTitleResId;
    private int mBodyResId;
    private int mImageResId;

    OnBoardingPage(int titleResId, int bodyResId, int imageResId) {
        mTitleResId = titleResId;
        mBodyResId = bodyResId;
        mImageResId = imageResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getBodyResId() {
        return mBodyResId;
    }

    public int getImageResId() {
        return mImageResId;
    }

}
