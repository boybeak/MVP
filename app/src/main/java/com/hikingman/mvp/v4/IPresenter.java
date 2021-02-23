package com.hikingman.mvp.v4;

public interface IPresenter<V extends IView, M extends IModel> {
    void attachView(V v);
    void detachView();
    V getView();
}
