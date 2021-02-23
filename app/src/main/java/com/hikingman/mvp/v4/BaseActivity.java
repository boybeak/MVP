package com.hikingman.mvp.v4;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

public class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView {
    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = findPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private P findPresenter() {
        Field[] fields = this.getClass().getDeclaredFields();
        if (fields != null) {
            for (Field f : fields) {
                if (f.getAnnotation(AutoPresenter.class) != null) {
                    try {
                        P p = (P)f.getType().newInstance();
                        f.setAccessible(true);
                        f.set(this, p);
                        return p;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    protected P getPresenter() {
        return presenter;
    }

}
