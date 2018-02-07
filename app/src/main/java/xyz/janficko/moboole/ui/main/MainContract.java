package xyz.janficko.moboole.ui.main;

public interface MainContract {

    interface View {
        void onBottomBarItemClicked(android.view.View view);
        void hideBottomBar(boolean isHidden);
    }

}
