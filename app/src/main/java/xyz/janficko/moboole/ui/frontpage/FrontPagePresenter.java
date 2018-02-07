package xyz.janficko.moboole.ui.frontpage;

public class FrontPagePresenter implements FrontPageContract.Presenter{

    private FrontPageContract.View view;

    public FrontPagePresenter(FrontPageContract.View view){
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

}
