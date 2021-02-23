package com.hikingman.mvp.x;

import android.os.Handler;

import com.hikingman.mvp.v4.BasePresenter;
import com.hikingman.mvp.v4.IModel;
import com.hikingman.mvp.v4.IView;

public interface IUserContact {
    interface IUserView extends IView {
        void showUser(String name);
    }
    class IUserModel implements IModel {
        void loadUser(Runnable runnable) {
            new Handler().postDelayed(runnable, 4 * 1000);
        }
    }
    class IUserPresenter extends BasePresenter<IUserView, IUserModel> {

        public void loadUser() {
            getModel().loadUser(() -> {
                getView().showUser("A user !!");
            });
        }
    }
}
