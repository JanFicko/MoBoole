package xyz.janficko.moboole.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import xyz.janficko.moboole.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = setLayoutResId();
        if (layoutId != 0) {
            setContentView(layoutId);
        }
        ButterKnife.bind(this);
        TAG = this.getClass().getSimpleName();
    }

    @LayoutRes
    protected abstract int setLayoutResId();

    public void moveToNextFragment(@NonNull Fragment fragment) {
        moveToNextFragment(getSupportFragmentManager(), fragment, R.id.content_container);
    }

    protected void moveToNextFragment(@NonNull FragmentManager fragmentManager,
                                      @NonNull Fragment fragment, int frameId) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frameId, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.commit();
    }

    public void goToNextFragment(Fragment fragment) {}

    public void goToPreviousFragment(Fragment fragment) {}

}
