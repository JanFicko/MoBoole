package xyz.janficko.moboole.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutResId();
        if (layoutId != 0) {
            setContentView(layoutId);
        }
        ButterKnife.bind(this);
        TAG = this.getClass().getSimpleName();
    }

    @LayoutRes
    protected abstract int getLayoutResId();

}
