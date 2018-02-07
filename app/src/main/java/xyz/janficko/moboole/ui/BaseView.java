package xyz.janficko.moboole.ui;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

}
