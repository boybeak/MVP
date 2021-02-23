package com.hikingman.mvp.x;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hikingman.mvp.v4.IPresenter;
import com.hikingman.mvp.v4.IView;

import java.lang.reflect.ParameterizedType;

public class BaseMVPActivity<P extends IPresenter> extends AppCompatActivity implements IView {
    private P presenter;
    private void setPresenter(P p) {
        if (presenter != null) {
            presenter.detachView();
        }
        this.presenter = p;
        presenter.attachView(this);
    }
    protected P getPresenter() {
        return presenter;
    }

    @SuppressWarnings("unchecked")
    private void generatePresenter() {
        ParameterizedType baseMVPActivity = (ParameterizedType)this.getClass().getGenericSuperclass();
        Class<?> presenterType = (Class<?>) baseMVPActivity.getActualTypeArguments()[0];
        try {
            P presenter = (P) presenterType.newInstance();
            setPresenter(presenter);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generatePresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
